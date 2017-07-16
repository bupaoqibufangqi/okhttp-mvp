package com.jw.mymvp.mvp.presenter;

import android.os.SystemClock;

import com.jw.mymvp.mvp.modul.CommonModul;
import com.jw.mymvp.mvp.modul.ICommonModul;
import com.jw.mymvp.mvp.view.IMainFragment2;
import com.jw.mymvp.util.base.UIUtils;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment2Presenter implements IMainFragment1Presenter {
    private IMainFragment2 iMainFragment2;
    private ICommonModul commonModul;
    public MainFragment2Presenter(IMainFragment2 iMainFragment2){
        this.iMainFragment2 = iMainFragment2;
        commonModul = new CommonModul(this);
    }
    @Override
    public void init() {
        iMainFragment2.initData();
    }

    @Override
    public void onSuccess(String result, String which) {

    }

    @Override
    public void onFaile(String errorReson, String which) {

    }

    @Override
    public void getData() {
        iMainFragment2.showLoading();
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                iMainFragment2.dimissLoading();
                iMainFragment2.onSuccess("");
            }
        },1500);
    }
}
