package com.example.sunnyday.base;

import android.os.Bundle;

//UI回调接口
public interface UiCallBack {
    void initBeforeView(Bundle savedInstanceState);
    void initData(Bundle savedInstanceState);
    int getLayoutId();
}
