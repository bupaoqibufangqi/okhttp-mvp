package com.jw.mymvp.mvp.presenter;

import com.jw.mymvp.mvp.view.IMainView;

/**
 * Created by Administrator on 2017/7/15.
 */

public class MainPresenter implements IMainPresenter {
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    @Override
    public void init() {
        iMainView.initData();
    }

    @Override
    public void onSuccess(String result, String which) {

    }

    @Override
    public void onFaile(String errorReson, String which) {

    }
}
