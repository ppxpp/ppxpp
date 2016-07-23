package me.ppxpp.exchange.currency;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.ppxpp.exchange.BaseActivity;
import me.ppxpp.exchange.R;
import me.ppxpp.exchange.utils.ActivityUtils;

public class CurrencyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        CurrencyFragment mCurrencyFragment =
                (CurrencyFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mCurrencyFragment == null) {
            // Create the fragment
            mCurrencyFragment = CurrencyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mCurrencyFragment, R.id.contentFrame);
        }
    }

}
