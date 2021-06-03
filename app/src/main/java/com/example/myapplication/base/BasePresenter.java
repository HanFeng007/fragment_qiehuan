package com.example.myapplication.base;

import java.lang.ref.SoftReference;

/**
 * @param <V> 注入的view对象
 */
public class BasePresenter<V> {

    private SoftReference<V> viewSoft;

    public void attachView(V view) {
        viewSoft = new SoftReference<>(view);
    }

    protected V getView() {
        return viewSoft.get();
    }

    protected boolean isViewAttached() {
        return getView() != null && viewSoft != null;
    }

    public void detachView() {
        if (viewSoft != null) {
            viewSoft.clear();
        }
    }
}
