package me.ppxpp.exchange.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import me.ppxpp.exchange.data.Rate;
import me.ppxpp.exchange.data.source.RateDataSource;

/**
 * Created by zhouhao on 2016/7/23.
 */
public class RateLocalDataSource implements RateDataSource {



    private static RateLocalDataSource INSTANCE;

    public static RateLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RateLocalDataSource(context);
        }
        return INSTANCE;
    }

    private RateLocalDataSource(Context context){

    }


    @Override
    public void refreshRate() {

    }

    @Override
    public void loadRate(LoadRateCallback callback) {

    }

    @Override
    public void cacheRate(Rate rate) {

    }
}
