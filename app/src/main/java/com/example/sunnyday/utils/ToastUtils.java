package com.example.sunnyday.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showLongToast(Context context, CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
