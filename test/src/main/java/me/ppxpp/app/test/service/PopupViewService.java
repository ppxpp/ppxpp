package me.ppxpp.app.test.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PopupViewService extends Service {
    public PopupViewService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    Button mButton;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction("action_show");
        filter.addAction("action_hide");
        registerReceiver(mReceiver, filter);

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // API中以TYPE_开头的常量有23个
        //mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR ;
        // 设置期望的bitmap格式
        mLayoutParams.format = PixelFormat.RGBA_8888;

        // 以下属性在Layout Params中常见重力、坐标，宽高
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.flags = mLayoutParams.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void addView(){
        if (mButton == null) {
            mButton = new Button(this);
            mButton.setBackgroundColor(Color.RED);
            mButton.setLayoutParams(new WindowManager.LayoutParams(400, 100));
            mButton.setText("Button");
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "onClickButton", Toast.LENGTH_SHORT).show();
                }
            });
        }
        mWindowManager.addView(mButton, mLayoutParams);
    }

    private void removeView(){
        mWindowManager.removeView(mButton);
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action_show")){
                addView();
            }else if (action.equals("action_hide")){
                removeView();
            }
        }
    };
}
