package com.example.sunnyday.net;

import android.util.Log;

import com.example.sunnyday.MainActivity;
import com.example.sunnyday.base.BaseResponse;
import com.example.sunnyday.utils.ToastUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetCallBack<T> implements Callback<T> {
    private static final String TAG = "net";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null && response.body() != null && response.isSuccessful()) {
            BaseResponse baseResponse = new Gson().fromJson(new Gson().toJson(response.body()), BaseResponse.class);
            if (baseResponse.getCode() == 404) {
                Log.e("Warn", baseResponse.getData().toString());
            } else if (baseResponse.getCode() == 500) {
                Log.e("Warn", baseResponse.getData().toString());
            } else {
                //无异常，返回数据
                onSucceed(call, response);
                Log.e("Warn", "除404，500的其他情况");
            }
        } else {
            Log.e(TAG, "onResponse: ???????" + response );
            onFailed();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "onFailure: "+ t.toString());
        onFailed();
    }

    public abstract void onSucceed(Call<T> call, Response<T> response);

    public abstract void onFailed();
}
