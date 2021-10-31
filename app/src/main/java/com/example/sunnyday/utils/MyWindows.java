package com.example.sunnyday.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.sunnyday.R;

import java.util.HashMap;
import java.util.Map;

public class MyWindows {
    private MyWindows myWindows;
    private PopupWindow popupWindow;
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private WindowManager show;
    WindowManager.LayoutParams layoutParams;
    private Map<String, Object> map = new HashMap<>();

    //弹窗消失时关闭阴影
    public PopupWindow.OnDismissListener closeDismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            WindowManager.LayoutParams normal = ((Activity) context).getWindow().getAttributes();
            normal.alpha = 1f;
            ((Activity) context).getWindow().setAttributes(normal);
        }
    };

    public MyWindows(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        myWindows = this;
    }

    public MyWindows(Context context, Map<String, Object> map) {
        this.context = context;
        this.map = map;
        inflater = LayoutInflater.from(context);
        myWindows = this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 右侧显示  自适应大小
     *
     * @param mView
     */
    public void showRightPopupWindow(View mView) {
        popupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(mView);
        popupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.AnimationRightFade); //设置动画
        popupWindow.showAtLocation(mView, Gravity.RIGHT, 0, 0);
        setBackgroundAlpha(0.5f, context);
        WindowManager.LayoutParams normal = ((Activity) context).getWindow().getAttributes();
        normal.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(normal);
        popupWindow.setOnDismissListener(closeDismiss);
    }

    /**
     * 右侧显示  高度占满父布局
     *
     * @param mView
     */
    public void showRightPopupWindowMatchParent(View mView) {
        popupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setContentView(mView);
        popupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.AnimationRightFade); //设置动画
        popupWindow.showAtLocation(mView, Gravity.RIGHT, 0, 0);
        setBackgroundAlpha(0.5f, context);
        WindowManager.LayoutParams normal = ((Activity) context).getWindow().getAttributes();
        normal.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(normal);
        popupWindow.setOnDismissListener(closeDismiss);
    }


    /**
     * 底部显示
     *
     * @param mView
     */
    public void showBottomPopupWindow(View mView) {
        popupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(mView);
        popupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.AnimationBottomFade); //设置动画
        popupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(0.5f, context);
        WindowManager.LayoutParams normal = ((Activity) context).getWindow().getAttributes();
        normal.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(normal);
        popupWindow.setOnDismissListener(closeDismiss);
    }

    public static void setBackgroundAlpha(float bgAlpha, Context mContext) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    /**
     * 设置弹窗动画
     *
     * @param animId
     * @return showPopu
     */
    public MyWindows setAnim(int animId) {
        if (popupWindow != null) {
            popupWindow.setAnimationStyle(animId);
        }
        return myWindows;
    }

    public void closePopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
/*
    使用方法
    MyWindows myWindows = new MyWindows(MainActivity.this);
    View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.center_layout, null);
    myWindows.showCenterPopupWindow(mView);
    */

}
