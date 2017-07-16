package com.jw.mymvp.mvp.presenter;

import android.text.TextUtils;

import com.jw.mymvp.mvp.modul.CommonModul;
import com.jw.mymvp.mvp.modul.ICommonModul;
import com.jw.mymvp.mvp.view.ILoginView;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/15.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView loginView;
    private ICommonModul commonModul;
    private String loginFlag = "LoginFlag";

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        commonModul = new CommonModul(this);
    }

    @Override
    public void init() {
        loginView.getUserInfo();
    }

    @Override
    public void login(String url, HashMap params) {
        if (loginView.checkInput()){
            loginView.showLoading();
            commonModul.getKeyMapRequest2(url, params, loginFlag);
        }
    }

    @Override
    public void onSuccess(String result, String which) {
        loginView.dimissLoading();
        if (loginFlag.equals(which)) {//表明是登录接口的回调
            if (!TextUtils.isEmpty(result)) {
                loginView.loginSuccess(result);
            } else {
                loginView.loginEmpty();
            }
        }
    }

    @Override
    public void onFaile(String errorReson, String which) {
        loginView.netError();
    }
}
