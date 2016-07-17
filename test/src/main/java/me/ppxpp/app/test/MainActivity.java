package me.ppxpp.app.test;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

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
    }
}
