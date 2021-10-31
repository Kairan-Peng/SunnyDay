package com.example.sunnyday.contract;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class WeatherActivity extends AppCompatActivity {
    public WeatherContract.WeatherPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        setContentView(getLayoutId());
        initData(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    private void initBeforeView(Bundle savedInstanceState) {
        mPresenter =  createPresenter();
//        mPresenter.attach((WeatherContract.IWeatherView) this);
    }

    protected abstract WeatherContract.WeatherPresenter createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.detach((WeatherContract.IWeatherView) this);
    }

}
