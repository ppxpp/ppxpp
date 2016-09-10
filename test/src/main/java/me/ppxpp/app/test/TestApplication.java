package me.ppxpp.app.test;

import android.app.Application;
import android.content.Intent;

import me.ppxpp.app.test.service.PopupViewService;

/**
 * Created by zhouhao on 2016/9/4.
 */
public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, PopupViewService.class));
    }
}
