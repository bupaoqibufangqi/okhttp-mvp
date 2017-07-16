package com.jw.mymvp.mvp.modul;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/15.
 */

public interface ICommonModul {
    /**
     * post提交JSON数据
     * @param url 地址
     * @param jsonStringParams 参数
     * @param which 表示访问的是哪个接口,modul层不用管，在回调那里原样返回即可
     */
    void getPostJsonRequest(String url,String jsonStringParams,String which);

    /**
     * post提交键值对数据
     * @param url 地址
     * @param params 参数
     * @param which 表示访问的是哪个接口,modul层不用管，在回调那里原样返回即可
     */
    void getKeyMapRequest2(String url, HashMap params,String which);
}
