package me.ppxpp.app.test;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.ppxpp.library.Log.LogUtils;

public class NumberActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    TextView mTV1, mTV2;
    int mScrollY1, mScrollY2;
    int mValue = 0;

    Handler mUIHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        mTV1 = (TextView) findViewById(R.id.text1);
        mTV2 = (TextView) findViewById(R.id.text2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScrollY1 = 110;
        mScrollY2 = 830;
        mUIHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTV1.setScrollY(mScrollY1);
                mTV2.setScrollY(mScrollY2);
            }
        }, 1000);
    }

    public void scrollY(View view){
        mScrollY1 += 20;
        mTV1.setScrollY(mScrollY1);
        LogUtils.d(TAG, "scrollY1 = " + mScrollY1);
    }

    public void scrollY_r(View view){
        mScrollY1 -= 20;
        mTV1.setScrollY(mScrollY1);
        LogUtils.d(TAG, "scrollY1 = " + mScrollY1);
    }

    public void scrollY2(View view){
        mScrollY2 += 20;
        mTV2.setScrollY(mScrollY2);
        LogUtils.d(TAG, "scrollY2 = " + mScrollY2);
    }

    public void scrollY2_r(View view){
        mScrollY2 -= 20;
        mTV2.setScrollY(mScrollY2);
        LogUtils.d(TAG, "scrollY2 = " + mScrollY2);
    }

    public void change(View view){
        if (mValue == 10){
            mValue = 0;
        }
        mTV1.setText("" + mValue);
        mTV2.setText("" + mValue);
        mValue++;
    }
}
