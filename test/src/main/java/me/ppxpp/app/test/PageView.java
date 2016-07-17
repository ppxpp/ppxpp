package me.ppxpp.app.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by haoozhou on 2015/12/27.
 */
public class PageView extends LinearLayout {

    private static String TAG = "PageView";

    public PageView(Context context) {
        super(context);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 页面是否可以垂直滑动
     */
    private boolean mVerticalSlideable;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY
                || MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY){
            throw new IllegalStateException("PageView onMeasure mode must be EXACTLY");
        }
        PageLayout.LayoutParams layoutParams = null;
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp == null || !(lp instanceof PageLayout.LayoutParams)){
            setLayoutParams(new PageLayout.LayoutParams());
        }
        layoutParams = (PageLayout.LayoutParams) getLayoutParams();
        if (layoutParams == null){
            throw new IllegalArgumentException("PageView need PageLayout.LayoutParams");
        }
        layoutParams.width = MeasureSpec.getSize(widthMeasureSpec);
        layoutParams.height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        super.offsetTopAndBottom(offset);
        //Log.d(TAG,"offsetTopAndBottom, offset = " + offset);
    }
}
