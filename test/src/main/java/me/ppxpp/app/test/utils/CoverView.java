package me.ppxpp.app.test.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import me.ppxpp.app.test.R;

/**
 * Created by zhouhao on 2016/9/10.
 */
class CoverView extends TextView {

    public CoverView(Context context) {
        super(context);
    }

    public CoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private final float MAX_VOLUME = 0.7f;
    private final float MIN_VOLUME = 0.2f;
    private float mVolume = MAX_VOLUME;
    private float mScale = 0f;
    private Drawable mDrawable;
    private Rect mBound = new Rect(0, 0, 0, 0);
    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_RECORDING = 2;
    public static final int STATUS_RECOGNISE = 3;
    private int mStatus = STATUS_NORMAL;

    private Drawable getCoverDrawable() {
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.drawable.bg_stt_cover);
        }
        return mDrawable;
    }

    public void setScale(float scale) {
        mScale = scale;
        postInvalidate();
    }

    public void animateVolume(float volume) {
        if (mStatus != STATUS_RECORDING) {
            return;
        }
        if (volume > MAX_VOLUME) {
            volume = MAX_VOLUME;
        } else if (volume < MIN_VOLUME) {
            volume = MIN_VOLUME;
        }
        if (volume <= mVolume && mAnimatorSet != null && mAnimatorSet.isRunning()) {
            return;
        }
        if (mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }
        ObjectAnimator a1 = ObjectAnimator.ofFloat(this, "Volume", mVolume, volume)
                .setDuration(100);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(this, "Volume", volume, MIN_VOLUME)
                .setDuration(800);
        a2.setStartDelay(80);
        mAnimatorSet.playSequentially(a1, a2);
        mAnimatorSet.start();
    }

    public void setVolume(float volume) {
        if (volume > MAX_VOLUME) {
            volume = MAX_VOLUME;
        } else if (volume < MIN_VOLUME) {
            volume = MIN_VOLUME;
        }
        mVolume = volume;
        postInvalidate();
    }

    public void setStatus(int status) {
        if (mStatus == status) {
            return;
        }
        if (status == STATUS_NORMAL) {
            mStatus = status;
        } else if (status == STATUS_RECORDING) {
            mStatus = status;
        } else if (status == STATUS_RECOGNISE) {
            mStatus = status;
        } else {
            throw new RuntimeException("invalid status");
        }
        postInvalidate();
    }

    public int getStatus() {
        return mStatus;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawScale(canvas);
        if (mScale == 1.0f) {
            super.onDraw(canvas);
        }
    }

    private void drawScale(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int pivotX = getScalePivotX();
        int pivotY = getScalePivotY();
        float scale = mScale;
        /**
         * 计算出四个方向可运动的最大距离
         */
        int dif = getMaxSpaceDif(this);

        int xDis = (int) (dif * scale);
        int left = pivotX - xDis;
        left = left < 0 ? 0 : left;
        int right = pivotX + xDis;
        right = right > width ? width : right;

        int yDis = (int) (dif * scale);
        int top = pivotY - yDis;
        top = top < 0 ? 0 : top;
        int bottom = pivotY + yDis;
        bottom = bottom < height ? bottom : height;

        mBound.set(left, top, right, bottom);
        Drawable drawable = getCoverDrawable();
        drawable.setBounds(mBound);
        int alpha = 255;
        if (mStatus == STATUS_RECORDING) {
            int minAlpha = (int) (255 * MIN_VOLUME);
            int maxAlpha = 255;
            alpha = (int) ((mVolume - MIN_VOLUME) / (MAX_VOLUME - MIN_VOLUME) * (maxAlpha - minAlpha)) + minAlpha;
        }
        drawable.setAlpha(alpha);
        drawable.draw(canvas);
    }


    /**
     * 计算出四个方向可运动的最大距离
     */
    private int getMaxSpaceDif(View view){
        int xDif = getMaxXSpaceDif(view);
        int yDif = getMaxYSpaceDif(view);
        int dif = xDif > yDif ? xDif : yDif;
        return dif;
    }

    /**
     * 获取X轴的可运动最大距离
     * @return
     */
    private int getMaxXSpaceDif(View view){
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
    private int getMaxYSpaceDif(View view){
        int height = view.getHeight();
        int pivotY = getScalePivotY();
        int topDif = pivotY - 0;
        int bottomDif = height - pivotY;
        int yDif = topDif > bottomDif ? topDif : bottomDif;
        return yDif;
    }

    private int mPivotX;
    private int getScalePivotX(){
        return mPivotX;
    }
    public void setScalePivotX(int pivotX){
        mPivotX = pivotX;
    }



    private int mPivotY;
    public void setScalePivotY(int pivotY){
        mPivotY = pivotY;
    }
    private int getScalePivotY(){
        return mPivotY;
    }
}
