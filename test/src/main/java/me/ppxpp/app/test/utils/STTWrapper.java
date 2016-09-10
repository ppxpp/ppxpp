package me.ppxpp.app.test.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;

/**
 * Created by zhouhao on 2016/9/10.
 */
public class STTWrapper {
    private String TAG = getClass().getSimpleName();
    private Context mContext;

    private EditText mEditText;
    private View mMicIcon;
    private String mContent;
    private String mHint;
    private RelativeLayout mContainer;
    private CoverView mCoverView;
    private WeakReference<CoverView> mCoverViewRef;

    private boolean mIsCoverAdded = false;

    public STTWrapper(Context context){
        if (context == null){
            throw new RuntimeException("context is null");
        }
        mContext = context.getApplicationContext();
    }


    public void setEditText(RelativeLayout container, EditText editText){
        setEditText(container, editText, null);
    }

    public void setEditText(RelativeLayout container, EditText editText, View icon){
        if (editText == null || container == null){
            throw new RuntimeException("editText or Container is null");
        }
        mContainer = container;
        mEditText = editText;
        mMicIcon = icon;
    }

    private void resetCoverView(CoverView coverView){
        mCoverView.setStatus(CoverView.STATUS_NORMAL);
        coverView.setText("请直接说“周杰伦”");
        coverView.setGravity(Gravity.CENTER);
        coverView.setScale(0f);
        coverView.setVolume(1.0f);
        coverView.setScalePivotX(getScalePivotX());
        coverView.setScalePivotY(getScalePivotY());
    }

    public int getScalePivotX(){
        int pivot;
        if (mMicIcon != null){
            pivot = mMicIcon.getLeft() + mMicIcon.getWidth() / 2;
        }else{
            pivot = mEditText.getWidth() - 50;
        }
        return pivot;
    }

    public int getScalePivotY(){
        int pivot;
        if (mMicIcon != null){
            pivot = mMicIcon.getTop() + mMicIcon.getHeight() / 2;
        }else{
            pivot = mEditText.getHeight() / 2;
        }
        return pivot;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onStart(){
        if (mIsCoverAdded){
            return;
        }
        mContent = mEditText.getText().toString();
        mHint = mEditText.getHint().toString();
        if (mCoverViewRef == null || mCoverViewRef.get() == null){
            mCoverView = new CoverView(mContext);
            resetCoverView(mCoverView);
            mCoverViewRef = new WeakReference<>(mCoverView);
        }else{
            mCoverView = mCoverViewRef.get();
            resetCoverView(mCoverView);
        }
        mCoverView.setOnClickListener(mOnClickListener);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mEditText.getLayoutParams());
        int idx = 0;
        for (int i = 0; i < mContainer.getChildCount(); i++){
            if (mContainer.getChildAt(i) == mEditText){
                idx = i;
                break;
            }
        }
        mContainer.addView(mCoverView, idx + 1, params);
        mEditText.setEnabled(false);
        mEditText.setText("");
        mEditText.setHint("");
        mIsCoverAdded = true;
        float y = (float) getScalePivotY();
        float space = (float)getMaxSpaceDif(mEditText);
        float midScale = y / space;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(
                ObjectAnimator.ofFloat(mCoverView, "Scale", 0f, midScale)
                        .setDuration(200),
                ObjectAnimator.ofFloat(mCoverView, "Scale", midScale, 1.0f)
                        .setDuration(200)
        );
        animatorSet.start();
    }

    public void onVolumeChanged(float volume){
        if (mIsCoverAdded && mCoverView != null){
            Log.d(TAG, "onVolumeChanged: " + volume);
            if (mCoverView.getStatus() != CoverView.STATUS_RECORDING){
                mCoverView.setStatus(CoverView.STATUS_RECORDING);
            }
            mCoverView.animateVolume(volume);
        }
    }

    public void onRecognise(){
        if (mIsCoverAdded && mCoverView != null){
            mCoverView.setStatus(CoverView.STATUS_RECOGNISE);
            mCoverView.setText("正在识别");
        }
    }



    public void onResult(boolean isSuccess, String text){
        if (mCoverView == null || !mIsCoverAdded){
            return;
        }
        if (isSuccess){
            mContent = text;
        }
        float y = (float) getScalePivotY();
        float space = (float)getMaxSpaceDif(mEditText);
        float midScale = y / space;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(
                ObjectAnimator.ofFloat(mCoverView, "Scale", 1.0f, midScale)
                        .setDuration(200),
                ObjectAnimator.ofFloat(mCoverView, "Scale", midScale, 0f)
                        .setDuration(200)
        );
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!TextUtils.isEmpty(mContent)){
                    mEditText.setText(mContent);
                }else if (!TextUtils.isEmpty(mHint)){
                    mEditText.setHint(mHint);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mContainer.removeView(mCoverView);
                mCoverView.setOnClickListener(null);
                mEditText.setEnabled(true);
                mIsCoverAdded = false;
                mCoverView = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "CoverView onClick");
        }
    };

    /**
     * 计算出四个方向可运动的最大距离
     */
    public int getMaxSpaceDif(View view){
        int xDif = getMaxXSpaceDif(view);
        int yDif = getMaxYSpaceDif(view);
        int dif = xDif > yDif ? xDif : yDif;
        return dif;
    }

    /**
     * 获取X轴的可运动最大距离
     * @return
     */
    public int getMaxXSpaceDif(View view){
        int width = view.getWidth();
        int pivotX = getScalePivotX();
        int leftDif = pivotX - 0;
        int rightDit = width - pivotX;
        int xDif = leftDif > rightDit ? leftDif : rightDit;
        return xDif;
    }

    /**
     * 获取Y轴的可运动最大距离
     * @return
     */
    public int getMaxYSpaceDif(View view){
        int height = view.getHeight();
        int pivotY = getScalePivotY();
        int topDif = pivotY - 0;
        int bottomDif = height - pivotY;
        int yDif = topDif > bottomDif ? topDif : bottomDif;
        return yDif;
    }

}
