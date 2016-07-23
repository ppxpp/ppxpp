package me.ppxpp.exchange.data.source.remote;

import me.ppxpp.exchange.data.Rate;
import me.ppxpp.exchange.data.source.RateDataSource;

/**
 * Created by zhouhao on 2016/7/23.
 */
public class RateRemoteDataSource implements RateDataSource{

    private static RateRemoteDataSource INSTANCE;

    public static RateRemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RateRemoteDataSource();
        }
        return INSTANCE;
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
