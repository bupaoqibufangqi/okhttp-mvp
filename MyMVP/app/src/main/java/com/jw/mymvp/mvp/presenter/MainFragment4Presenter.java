package com.jw.mymvp.mvp.presenter;

import com.jw.mymvp.mvp.modul.CommonModul;
import com.jw.mymvp.mvp.modul.ICommonModul;
import com.jw.mymvp.mvp.view.IMainFragment4;
import com.jw.mymvp.util.base.UIUtils;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment4Presenter implements IMainFragment4Presenter {
    private IMainFragment4 iMainFragment4;
    private ICommonModul commonModul;

    public MainFragment4Presenter(IMainFragment4 iMainFragment4) {
        this.iMainFragment4 = iMainFragment4;
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
        iMainFragment4.showLoading();
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                iMainFragment4.dimissLoading();
                iMainFragment4.netError();;
            }
        },1500);
    }
}
