package com.example.sunnyday.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.sunnyday.R;
import com.example.sunnyday.bean.CityResponse;

import java.util.List;

public class ProvinceAdapter extends BaseQuickAdapter<CityResponse, BaseViewHolder> {

    public ProvinceAdapter(int layoutResId, @Nullable List<CityResponse> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, CityResponse cityResponse) {
        baseViewHolder.setText(R.id.tv_city, cityResponse.getName());
//        baseViewHolder.findView(R.id.item_city).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
