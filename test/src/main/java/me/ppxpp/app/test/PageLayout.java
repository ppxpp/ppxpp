package me.ppxpp.app.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import me.ppxpp.library.widget.ViewDragHelper;

/**
 * TODO: document your custom view class.
 */
public class PageLayout extends ViewGroup {
    private String TAG = getClass().getSimpleName();

    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    public PageLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public PageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PageLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PageLayout, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.PageLayout_exampleString);
        mExampleColor = a.getColor(
                R.styleable.PageLayout_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.PageLayout_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.PageLayout_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.PageLayout_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }
        a.recycle();
    }

    private int PageMargin = 100;
    private Rect mRightRect;
    private Rect mCenterRect;
    private ViewDragHelper mViewDragHelper;

    private ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (getChildCount() > 1&& getChildAt(0) == child){
                //return false;
            }
            return true;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            //int childHeight = child.getHeight();
            return 200;//child.getBottom();
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 0;//super.getViewHorizontalDragRange(child);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return child.getLeft();//super.clampViewPositionHorizontal(child, left, dx);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (top > 300){
                //return top - dy / 2;
            }
            return top;//super.clampViewPositionVertical(child, top, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            View view = releasedChild;
            if (view instanceof  PageView){
                PageView pageView = (PageView) view;
                PageLayout.LayoutParams layoutParams = (LayoutParams) pageView.getLayoutParams();
                mViewDragHelper.settleCapturedViewAt(layoutParams.left, layoutParams.top);
                //mViewDragHelper.flingCapturedView(0, 0, 1000, 500);
                ViewCompat.postInvalidateOnAnimation(PageLayout.this);
            }
            /*mViewDragHelper.settleCapturedViewAt(0, 0);
            int top = releasedChild.getTop();
            Log.d(TAG, "top:"+top+", xvel:"+xvel+", yvel:"+yvel);*/
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
    };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)){
            Log.d(TAG, "continueSettling true");
            ViewCompat.postInvalidateOnAnimation(this);
        }else{
            Log.d(TAG, "continueSettling ----");
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mViewDragHelper == null){
            mViewDragHelper = ViewDragHelper.create(this, mDragCallback);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
        //return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.d(TAG, "width, mode = " + widthMode + ", size = " + widthSize);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "height, mode = " + heightMode + ", size = " + heightSize);

        int pageWidth = widthSize / 3;
        int pageHeight = heightSize / 3 * 2;
        for (int i = 0; i < getChildCount(); i++){
            View view = getChildAt(i);
            view.measure(MeasureSpec.makeMeasureSpec(pageWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(pageHeight, MeasureSpec.EXACTLY));
            //calculate view's location
        }
    }

    @Override
    public void addView(View child, int index) {
        //generate layout params
        LayoutParams lp = new LayoutParams();
        child.setLayoutParams(lp);
        super.addView(child, index);
    }

    private Rect calculateCenterRect(int pageWidth, int pageHeight, int viewWidth, int viewHeight){
        Rect rect = new Rect();
        rect.left = viewWidth / 2 - pageWidth / 2;
        rect.top = viewHeight / 2 - pageHeight / 2;
        rect.right = viewWidth / 2 + pageWidth / 2;
        rect.bottom = viewHeight / 2 + pageHeight / 2;
        return rect;
    }

    private Rect calculateRightRect(int pageWidth, int pageHeight, int viewWidth, int viewHeight){
        Rect rect = new Rect();
        rect.left = viewWidth / 2 + PageMargin / 2;
        rect.top = viewHeight / 2 - pageHeight / 2;
        rect.right = rect.left + pageWidth;
        rect.bottom = rect.top + pageHeight;
        return rect;
    }

    private void initChildLayoutParams(){
        if (getChildCount() == 1){
            View child = getChildAt(0);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (layoutParams.left == 0
                    && layoutParams.top == 0
                    && layoutParams.right == 0
                    && layoutParams.bottom == 0){
                layoutParams.applyRect(mCenterRect);
            }
        }else {
            for (int i = getChildCount() - 1; i >= 0; i--) {
                View child = getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                if (layoutParams.left == 0
                        && layoutParams.top == 0
                        && layoutParams.right == 0
                        && layoutParams.bottom == 0) {
                    int childWidth = layoutParams.width;
                    int horizontalOffset = (childWidth + PageMargin) * (getChildCount() - i - 1);
                    mRightRect.offset(-horizontalOffset, 0);
                    layoutParams.applyRect(mRightRect);
                    mRightRect.offset(horizontalOffset, 0);
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout, changed = " + changed
                + ", l:"+l+",t:"+t+",r:"+r+",b:"+b);
        int childCount = getChildCount();
        Log.d(TAG,"child count = " + childCount);
        if ((changed || mCenterRect == null) && childCount > 0){
            int pageWidth = getChildAt(0).getMeasuredWidth();
            int pageHeight = getChildAt(0).getMeasuredHeight();
            mCenterRect = calculateCenterRect(pageWidth, pageHeight, r - l, b - t);
        }
        if ((changed || mRightRect == null) && childCount > 0){
            int pageWidth = getChildAt(0).getMeasuredWidth();
            int pageHeight = getChildAt(0).getMeasuredHeight();
            mRightRect = calculateRightRect(pageWidth, pageHeight, r - l, b - t);
        }
        initChildLayoutParams();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            child.layout(layoutParams.left,
                    layoutParams.top,
                    layoutParams.right,
                    layoutParams.bottom);
        }
    }


    public static class LayoutParams extends ViewGroup.LayoutParams{

        public int left;
        public int top;
        public int right;
        public int bottom;

        public LayoutParams() {
            this(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }

        public LayoutParams(int width, int height){
            super(width, height);
        }

        public void applyRect(Rect rect){
            left = rect.left;
            top = rect.top;
            right = rect.right;
            bottom = rect.bottom;
        }
    }
}
