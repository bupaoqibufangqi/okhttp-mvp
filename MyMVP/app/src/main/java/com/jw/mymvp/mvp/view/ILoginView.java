package com.jw.mymvp.mvp.view;

import com.jw.mymvp.bean.UserInfo;
/**
 * Created by Administrator on 2017/7/15.
 */

public interface ILoginView{
    void showLoading();
    void dimissLoading();
    void loginSuccess(String result);
    void loginEmpty();
    void netError();
    /**
     * 获取用户信息
     */
    void getUserInfo();

    /**
     * 保存用户信息
     */
    void saveUserInfo(UserInfo userInfo);
    boolean checkInput();
    void login();
    void toMainActivity();
}
