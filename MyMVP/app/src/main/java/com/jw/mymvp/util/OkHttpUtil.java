package com.jw.mymvp.util;

import android.content.Context;

import com.jw.mymvp.util.base.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jw on 2016/1/28.
 */
public class OkHttpUtil {
    /**
     * 据说官方文档建议用同一个client
     */
    private static OkHttpClient mOkHttpClient;
    private static String cacheFilePath;//缓存路径

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");//JSON形式传参
    public static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//表单形式传参

    public synchronized static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(false);

            //暂时不使用缓存
//            cacheFilePath = context.getFilesDir().getPath()+"/OkCacheResponse.tmp";
//            int cacheSize = 10 * 1024 * 1024; // 10 MiB
//            Cache cache = new Cache(new File(cacheFilePath),cacheSize);
//            builder.cache(cache);//设置本地缓存
            mOkHttpClient = builder.build();//构造自定义属性的mOkHttpClient;
        }
        return mOkHttpClient;
    }


    /**
     * post提交JSON的方式
     */
    public Request getPostJsonRequest(String url, String jsonStringParams) {
        RequestBody body = RequestBody.create(JSON, jsonStringParams);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    /**
     * post提交键值对的方式1
     */
    public Request getKeyMapRequest(String url, String keyMapStringParams) {
        RequestBody body = RequestBody.create(FORM, keyMapStringParams);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    /**
     * post提交键值对的方式2
     */
    public Request getKeyMapRequest2(String url, HashMap params) {
        params.toString();
        Set set = params.entrySet();
        FormBody.Builder builder = new FormBody.Builder();
        for(Iterator iter = set.iterator(); iter.hasNext();)
        {
            Map.Entry entry = (Map.Entry)iter.next();
            String key = (String)entry.getKey();
            String value = entry.getValue() == null ? null : (String)entry.getValue();
            if (value != null && !value.equals(null) && !value.equals("") && !value.equals("null")){
                builder.add(key,value);
            }
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return request;
    }

    public void Ok_Get() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .build();
        //new call
        final Call call = mOkHttpClient.newCall(request);
//        //请求加入调度,异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i("请求失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.i("请求成功" + response.body().string());
            }
        });
    }
}
