package me.ppxpp.app.test;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by zhouhao on 2016/2/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTestExample {

    @Mock
    Context mContext;

    @Test
    public void testStringFromContext(){
        System.out.println("s+"+mContext.getString(R.string.app_name));
        String packageName = mContext.getApplicationInfo().toString();//.packageName;
        Assert.assertEquals(packageName, "sss");
    }
}
