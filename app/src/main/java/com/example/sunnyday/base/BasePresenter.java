package com.example.sunnyday.base;

import java.lang.ref.WeakReference;

/*
Presenter 基类，操作视图View
@param <V>
*/
public class BasePresenter<V extends BaseView> {
    private WeakReference<V> mWeakReference;

    /*
        关联View
        @param V
    */
    public void attach(V v) {
        mWeakReference = new WeakReference<V>(v);
    }

    /*
        分离View
        @param V
    */
    public void detach(V v) {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /*
        获得View
        @return V
    */
    public V getView() {
        if (mWeakReference != null) {
            return mWeakReference.get();
        }
        return null;
    }
}
