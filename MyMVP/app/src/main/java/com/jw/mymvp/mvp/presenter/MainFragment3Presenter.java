package com.jw.mymvp.mvp.presenter;

import com.jw.mymvp.mvp.modul.CommonModul;
import com.jw.mymvp.mvp.modul.ICommonModul;
import com.jw.mymvp.mvp.view.IMainFragment3;
import com.jw.mymvp.util.base.UIUtils;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MainFragment3Presenter implements IMainFragment3Presenter {
    private IMainFragment3 iMainFragment3;
    private ICommonModul commonModul;

    public MainFragment3Presenter(IMainFragment3 iMainFragment3) {
        this.iMainFragment3 = iMainFragment3;
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
        iMainFragment3.showLoading();
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                iMainFragment3.dimissLoading();
                iMainFragment3.onEmpty();;
            }
        },1500);
    }
}
