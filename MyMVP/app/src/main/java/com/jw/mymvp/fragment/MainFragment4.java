package com.jw.mymvp.fragment;

import android.view.View;
import android.widget.TextView;
import com.jw.mymvp.R;
import com.jw.mymvp.fragment.base.BaseFragment;
import com.jw.mymvp.mvp.presenter.MainFragment4Presenter;
import com.jw.mymvp.mvp.view.IMainFragment4;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment4 extends BaseFragment implements IMainFragment4 {
    @Bind(R.id.tv_content)
    TextView tvContent;

    private MainFragment4Presenter mainFragment4Presenter;

    public MainFragment4Presenter getMainFragment1Presenter(){
        if (mainFragment4Presenter == null){
            mainFragment4Presenter = new MainFragment4Presenter(this);
        }
        return mainFragment4Presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_1;
    }

    @Override
    public void initUi(View view) {
        tvContent.setText("主页面4");
        mainFragment4Presenter = new MainFragment4Presenter(this);
    }

    @Override
    public void afterActivityCreat() {
        mainFragment4Presenter.getData();
    }

    @Override
    public void getData() {
        mainFragment4Presenter.getData();
    }

    @Override
    public void onSuccess(String result) {
        dimissLoading();
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
