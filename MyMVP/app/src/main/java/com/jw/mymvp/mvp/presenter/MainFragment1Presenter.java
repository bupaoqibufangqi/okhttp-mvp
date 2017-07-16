package com.jw.mymvp.mvp.presenter;

import android.os.SystemClock;

import com.jw.mymvp.mvp.modul.CommonModul;
import com.jw.mymvp.mvp.modul.ICommonModul;
import com.jw.mymvp.mvp.view.IMainFragment1;
import com.jw.mymvp.util.base.UIUtils;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment1Presenter implements IMainFragment1Presenter {
    private IMainFragment1 iMainFragment1;
    private ICommonModul commonModul;

    public MainFragment1Presenter(IMainFragment1 iMainFragment1) {
        this.iMainFragment1 = iMainFragment1;
        commonModul = new CommonModul(this);
    }

    @Override
    public void init() {
    }

    @Override
    public void onSuccess(String result, String which) {

    }

    @Override
    public void onFaile(String errorReson, String which) {

    }

    @Override
    public void getData() {
        iMainFragment1.showLoading();
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                iMainFragment1.dimissLoading();
                iMainFragment1.onSuccess("");;
            }
        },1500);
    }
}
