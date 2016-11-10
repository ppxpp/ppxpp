package me.ppxpp.app.test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhouhao on 2016/11/10.
 */
public class NumberView extends TextView {
    public NumberView(Context context) {
        super(context);
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
