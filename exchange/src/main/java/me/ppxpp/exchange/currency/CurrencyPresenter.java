package me.ppxpp.exchange.currency;

import android.support.annotation.NonNull;

import java.util.List;

import me.ppxpp.exchange.data.Currency;
import me.ppxpp.exchange.data.KeyboardCommand;
import me.ppxpp.exchange.data.source.RateRepository;

/**
 * Created by zhouhao on 2016/7/23.
 */
public class CurrencyPresenter implements CurrencyContract.Presenter {

    CurrencyContract.View mView;
    CurrencyContract.Keyboard mKeyboard;
    RateRepository mRateRepository;

    public CurrencyPresenter(RateRepository rateRepository){
        mRateRepository = rateRepository;
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadCurrencies(boolean forceUpdate) {

    }

    @Override
    public void addNewCurrency() {

    }

    @Override
    public void deleteCurrency(@NonNull Currency currency) {

    }

    @Override
    public void changeCurrency() {

    }

    @Override
    public void setCurrencyHighlight(Currency currency) {

    }

    @Override
    public void setCurrencyNormal(Currency currency) {

    }

    @Override
    public void calculateCurrencies(Currency targetCurrency, List<Currency> currencies) {

    }

    @Override
    public void updateCurrencyValue(Currency currency) {

    }

    @Override
    public void processKeyboardCommand(KeyboardCommand keyboardCommand) {

    }

    @Override
    public void openKeyboard() {

    }

    @Override
    public void closeKeyboard() {

    }

    @Override
    public void moveCurrencyPosition(int fromPosition, int toPosition) {

    }

    @Override
    public void start() {

    }
}
