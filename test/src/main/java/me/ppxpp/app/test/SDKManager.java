package me.ppxpp.app.test;

/**
 * Created by zhouhao on 2016/2/14.
 */
public class SDKManager {

    private static SDKManager mInstance;

    public static SDKManager instance(){
        if (mInstance == null){
            mInstance = new SDKManager();
        }
        return mInstance;
    }

    public int getVersion(){
        return 13;
    }

}
