package com.jw.mymvp.mvp.presenter;

import com.jw.mymvp.mvp.presenter.base.IBasePresenter;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/15.
 */

public interface ILoginPresenter extends IBasePresenter {

    void login(String url, HashMap<String,String> params);
}
