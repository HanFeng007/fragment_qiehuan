package com.example.myapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected View view;
    private T mPresenter;

    /**
     * fragment创建时执行的方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
    }

    /**
     * @return Presenter层
     */
    protected abstract T createPresenter();

    /**
     * @return 返回一个view对象
     * 关于fragment完成创建的过程中, 进行页面渲染的方法
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initFragmentView(inflater);
        return view;
    }

    /**
     * 加载fragment布局
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initFragmentChildView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        initFragmentData(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 数据填充
     */
    protected abstract void initFragmentData(Bundle savedInstanceState);

    /**
     * findViewById操作
     */
    protected abstract void initFragmentChildView(View view);

    /**
     * 加载fragment布局
     */
    protected abstract View initFragmentView(LayoutInflater inflater);
}
