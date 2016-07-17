package me.ppxpp.app.test.layout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by zhouhao on 2016/2/14.
 */
public class MyLayoutManager extends RecyclerView.LayoutManager {

    private static String TAG = MyLayoutManager.class.getSimpleName();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
        return params;
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;//super.supportsPredictiveItemAnimations();
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, "isPreLayout? " + state.isPreLayout());
        Log.d(TAG, "onLayoutChildren: " + getItemCount());
        if (getItemCount() == 0){
            //removeAndRecycleAllViews(recycler);
            detachAndScrapAttachedViews(recycler);
            return;
        }
        int childTop = 0;
        int childLeft = 0;
        int chileWidth = 0;
        int childHeight = 0;
        Log.d(TAG,"getChildCount() = " + getChildCount());
        if (getChildCount() == 0){
            //
            View view = recycler.getViewForPosition(0);
            addView(view);
            measureChild(view, 0, 0);
            chileWidth = getDecoratedMeasuredWidth(view);
            childHeight = getDecoratedMeasuredHeight(view);
            Log.d(TAG, "childWidth:" + chileWidth + ", childHeight:"+ childHeight);
            removeAndRecycleView(view, recycler);

            fill(recycler, state, 0, 0, chileWidth, childHeight);
        }

    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state,
                      int left, int top, int viewWidth, int viewHeight){

        int firstPosition = 0;
        int verticalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
        int maxRow = verticalSpace / viewHeight + 1;
        int curtPosition = firstPosition;
        Log.d(TAG, "verticalSpace:"+verticalSpace + ", maxRow = " + maxRow);
        for (int i = 0; i < maxRow; i++, curtPosition++){
            View child = recycler.getViewForPosition(curtPosition);
            addView(child);
            measureChild(child, 0, 0);
            int l = left;
            int t = top + i * viewHeight;
            int r = left + viewWidth;
            int b = t + viewHeight;
            layoutDecorated(child, l, t, r, b);
            //layoutDecorated(child,left,top + i * viewHeight, left + viewWidth, top + (i + 1) * viewHeight);
        }

    }
}
