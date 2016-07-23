package me.ppxpp.exchange.data.source;

import me.ppxpp.exchange.data.Rate;

/**
 * Created by zhouhao on 2016/7/23.
 */
public interface RateDataSource {

    interface LoadRateCallback{

        void onRateLoaded(Rate rate);

        void onLoadedError();
    }

    void refreshRate();

    void loadRate(LoadRateCallback callback);

    void cacheRate(Rate rate);
}
