package com.jw.mymvp.mvp.presenter.base;

/**
 * Created by Administrator on 2017/7/15.
 */

public interface IBasePresenter {
    void init();
    /**
     * modul层成功访问网络后所调用的方法
     * @param result  返回结果
     * @param which 判断当前结果是哪个方法的回调
     */
    void onSuccess(String result,String which);
    /**
     * modul层访问网络失败后所调用的方法
     * @param errorReson  返回结果
     * @param which 判断当前结果是哪个方法的回调
     */
    void onFaile(String errorReson,String which);
}
