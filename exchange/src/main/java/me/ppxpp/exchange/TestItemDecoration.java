package me.ppxpp.exchange;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhouhao on 2016/7/17.
 */
public class TestItemDecoration extends RecyclerView.ItemDecoration {

    Drawable mDivider;

    public TestItemDecoration(Context context){
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider_test);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(100, 20, 20, 20);
        //outRect.top = 20;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int top = child.getTop() - params.topMargin - 20;// + Math.round(ViewCompat.getTranslationY(child));
            //final int bottom = top + mDivider.getIntrinsicHeight();
            final int bottom = child.getBottom() + params.bottomMargin + 20;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /*@Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int top = child.getTop() - params.topMargin + 20;// + Math.round(ViewCompat.getTranslationY(child));
            //final int bottom = top + mDivider.getIntrinsicHeight();
            final int bottom = child.getBottom() + params.bottomMargin - 20;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }*/
}
