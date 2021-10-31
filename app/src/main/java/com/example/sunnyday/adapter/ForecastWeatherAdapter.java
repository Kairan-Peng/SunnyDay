package com.example.sunnyday.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.sunnyday.R;
import com.example.sunnyday.bean.ForecastWeatherResponse;


import java.util.List;

public class ForecastWeatherAdapter extends BaseQuickAdapter<ForecastWeatherResponse.HeWeather6Bean.DailyForecastBean, BaseViewHolder> {
    public ForecastWeatherAdapter(int layoutResId, @Nullable List<ForecastWeatherResponse.HeWeather6Bean.DailyForecastBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ForecastWeatherResponse.HeWeather6Bean.DailyForecastBean dailyForecastBean) {
        baseViewHolder.setText(R.id.tv_date_rv, dailyForecastBean.getDate())
                .setText(R.id.tv_low_and_height_rv, dailyForecastBean.getTmpMin()+"℃ - "+dailyForecastBean.getTmpMax() + "℃")
                .setText(R.id.tv_info_rv, dailyForecastBean.getCondTxtD());
    }
}
