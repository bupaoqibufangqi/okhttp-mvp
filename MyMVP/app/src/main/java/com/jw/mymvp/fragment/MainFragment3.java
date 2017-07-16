package com.jw.mymvp.fragment;

import android.view.View;
import android.widget.TextView;
import com.jw.mymvp.R;
import com.jw.mymvp.fragment.base.BaseFragment;
import com.jw.mymvp.mvp.presenter.MainFragment3Presenter;
import com.jw.mymvp.mvp.view.IMainFragment3;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment3 extends BaseFragment implements IMainFragment3 {
    @Bind(R.id.tv_content)
    TextView tvContent;

    private MainFragment3Presenter mainFragment3Presenter;

    public MainFragment3Presenter getMainFragment1Presenter(){
        if (mainFragment3Presenter == null){
            mainFragment3Presenter = new MainFragment3Presenter(this);
        }
        return mainFragment3Presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_1;
    }

    @Override
    public void initUi(View view) {
        tvContent.setText("主页面3");
        mainFragment3Presenter = new MainFragment3Presenter(this);
    }

    @Override
    public void afterActivityCreat() {
        mainFragment3Presenter.getData();
    }

    @Override
    public void getData() {
        mainFragment3Presenter.getData();
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
