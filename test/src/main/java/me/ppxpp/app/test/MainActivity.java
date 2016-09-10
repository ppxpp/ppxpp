package me.ppxpp.app.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    LinearLayout mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_page_layout);
        getSupportActionBar().hide();
        /*PageView pageView = new PageView(this);
        pageView.setId(R.id.page_view);
        PageLayout pageLayout = (PageLayout) findViewById(R.id.page_layout);
        pageLayout.addView(pageView);
        //View pageView = findViewById(R.id.page_view);
        Fragment fragment = BlankFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_view, fragment).commit();*/

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // API中以TYPE_开头的常量有23个
        //mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR ;
        // 设置期望的bitmap格式
        mLayoutParams.format = PixelFormat.RGBA_8888;

        // 以下属性在Layout Params中常见重力、坐标，宽高
        mLayoutParams.gravity = Gravity.BOTTOM;
        mLayoutParams.flags = mLayoutParams.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        mLayoutParams.width = 1080;//WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = 1920;//WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("show");
        filter.addAction("hide");
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    public void show(){
        if (mButton == null) {
            mButton = new LinearLayout(this);
            mButton.setBackgroundColor(Color.parseColor("#52ff0000"));
            mButton.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
            //mButton.setText("Button");
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Activity onClickButton", Toast.LENGTH_SHORT).show();
                }
            });
        }
        mWindowManager.addView(mButton, mLayoutParams);
    }

    public void hide(){
        mWindowManager.removeView(mButton);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("show")){
                show();
            }else if (action.equals("hide")){
                hide();
            }
        }
    };
}
