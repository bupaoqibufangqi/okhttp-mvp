package com.jw.mymvp.mvp.modul;

import com.jw.mymvp.mvp.presenter.base.IBasePresenter;
import com.jw.mymvp.util.OkHttpUtil;
import com.jw.mymvp.util.base.UIUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/15.
 */

public class CommonModul implements ICommonModul {
    private OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;
    private IBasePresenter basePresenter;

    public CommonModul(IBasePresenter basePresenter) {
        this.basePresenter = basePresenter;
        okHttpClient = OkHttpUtil.getOkHttpClient();
        okHttpUtil = new OkHttpUtil();
    }

    @Override
    public void getPostJsonRequest(String url, String jsonStringParams, final String which) {
        okHttpClient.newCall(okHttpUtil.getPostJsonRequest(url, jsonStringParams)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        basePresenter.onFaile(e.getMessage(), which);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            basePresenter.onSuccess(response.body().string(), which);
                        } catch (IOException e) {
                            e.printStackTrace();
                            basePresenter.onFaile(e.getMessage(), which);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void getKeyMapRequest2(String url, HashMap params, final String which) {
        okHttpClient.newCall(okHttpUtil.getKeyMapRequest2(url, params)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        basePresenter.onFaile(e.getMessage(), which);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            basePresenter.onSuccess(response.body().string(), which);
                        } catch (IOException e) {
                            e.printStackTrace();
                            basePresenter.onFaile(e.getMessage(), which);
                        }
                    }
                });
            }
        });
    }
}
