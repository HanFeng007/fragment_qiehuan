package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {

    private Context mContext;
    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = BaseActivity.this;
        initActivityView(savedInstanceState);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        findViewById();

        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取资源ID
     */
    protected abstract void findViewById();

    /**
     * 创建presenter的方法
     */
    protected abstract T createPresenter();

    /**
     * act界面填充的方法, 需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
