package me.ppxpp.exchange.currency;

import android.support.annotation.NonNull;

import java.util.List;

import me.ppxpp.exchange.BasePresenter;
import me.ppxpp.exchange.BaseView;
import me.ppxpp.exchange.data.Currency;
import me.ppxpp.exchange.data.KeyboardCommand;

/**
 * Created by zhouhao on 2016/7/23.
 */
public interface CurrencyContract {

    interface View extends BaseView<Presenter>{

        void showCurrencies(List<Currency> currencies);

        void showCompressContentView(Currency currency, boolean anim);

        void showFullContentView(Currency currency, boolean anim);

        void showCurrencyHighlight(Currency currency);

        void showCurrencyNormal(Currency currency);
    }

    interface Keyboard extends BaseView<Presenter>{

        void showKeyboard(boolean anim);

        void dismissKeyboard(boolean anim);
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void loadCurrencies(boolean forceUpdate);

        void addNewCurrency();

        void deleteCurrency(@NonNull Currency currency);

        void changeCurrency();

        void setCurrencyHighlight(Currency currency);

        void setCurrencyNormal(Currency currency);

        void calculateCurrencies(Currency targetCurrency, List<Currency> currencies);

        void updateCurrencyValue(Currency currency);

        void processKeyboardCommand(KeyboardCommand keyboardCommand);

        void openKeyboard();

        void closeKeyboard();

        void moveCurrencyPosition(int fromPosition, int toPosition);

    }

}
