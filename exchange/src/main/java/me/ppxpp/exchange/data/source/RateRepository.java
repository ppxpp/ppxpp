package me.ppxpp.exchange.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import me.ppxpp.exchange.data.Rate;
import me.ppxpp.exchange.data.source.local.RateLocalDataSource;
import me.ppxpp.exchange.data.source.remote.RateRemoteDataSource;


/**
 * Created by zhouhao on 2016/7/23.
 */
public class RateRepository implements RateDataSource {

    RateDataSource mRateLocalDataSource;
    RateDataSource mRateRemoteDataSource;

    private RateRepository(@NonNull RateDataSource tasksRemoteDataSource,
                            @NonNull RateDataSource tasksLocalDataSource) {
        mRateLocalDataSource = tasksRemoteDataSource;
        mRateRemoteDataSource = tasksLocalDataSource;
    }

    private static RateRepository INSTANCE;

    public static RateRepository getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new RateRepository(RateRemoteDataSource.getInstance(),
                    RateLocalDataSource.getInstance(context));
        }
        return INSTANCE;
    }

    private boolean mCacheIsDirty = false;
    private Rate mCachedRate;

    @Override
    public void refreshRate() {
        mCacheIsDirty = true;
    }

    @Override
    public void loadRate(LoadRateCallback callback) {
        if (mCachedRate != null && !mCacheIsDirty){
            callback.onRateLoaded(mCachedRate);
            return;
        }
        if (mCacheIsDirty){
            loadRateFromRemoteDataSource(callback);
        }else{
            mRateLocalDataSource.loadRate(new LoadRateCallback() {
                @Override
                public void onRateLoaded(Rate rate) {
                    cacheRate(rate);
                }

                @Override
                public void onLoadedError() {

                }
            });
        }
    }

    @Override
    public void cacheRate(Rate rate) {
        mCachedRate = rate;
        mCacheIsDirty = false;
    }

    private void loadRateFromRemoteDataSource(@NonNull final LoadRateCallback callback){
        mRateRemoteDataSource.loadRate(new LoadRateCallback() {
            @Override
            public void onRateLoaded(Rate rate) {
                //update cache
                cacheRate(rate);
                //update localDataSource
                mRateLocalDataSource.cacheRate(rate);

                callback.onRateLoaded(rate);
            }

            @Override
            public void onLoadedError() {
                callback.onLoadedError();
            }
        });
    }
}
