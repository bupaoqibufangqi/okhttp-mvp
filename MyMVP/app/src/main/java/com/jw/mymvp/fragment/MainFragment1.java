package com.jw.mymvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jw.mymvp.R;
import com.jw.mymvp.fragment.base.BaseFragment;
import com.jw.mymvp.mvp.presenter.MainFragment1Presenter;
import com.jw.mymvp.mvp.view.IMainFragment1;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment1 extends BaseFragment implements IMainFragment1 {
    @Bind(R.id.tv_content)
    TextView tvContent;
    private MainFragment1Presenter mainFragment1Presenter;

    public MainFragment1Presenter getMainFragment1Presenter() {
        if (mainFragment1Presenter == null) {
            mainFragment1Presenter = new MainFragment1Presenter(this);
        }
        return mainFragment1Presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_1;
    }

    @Override
    public void initUi(View view) {
        tvContent.setText("主页面1");
        mainFragment1Presenter = new MainFragment1Presenter(this);
    }

    @Override
    public void afterActivityCreat() {
        mainFragment1Presenter.getData();
    }

    @Override
    public void getData() {
        mainFragment1Presenter.getData();
    }

    @Override
    public void onSuccess(String result) {
    }

    @Override
    public void onEmpty() {
        showSpecificView(R.layout.page_empty);
    }

    @Override
    public void netError() {
        showSpecificView(R.layout.page_error);
    }

    @Override
    public void showLoading() {
        showSpecificView(R.layout.page_loading);
    }

    @Override
    public void dimissLoading() {
        dismissLoadingView();
    }
}
