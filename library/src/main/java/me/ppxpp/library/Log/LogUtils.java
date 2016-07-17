package me.ppxpp.library.Log;

import android.util.Log;

/**
 * Created by zhouhao on 2016/7/17.
 */
public class LogUtils {

    public static boolean bShowLog = true;

    public static void d(String TAG, String msg){
        if (bShowLog){
            Log.d(TAG, msg);
        }
    }
}
