package com.jw.mymvp.config;
import com.jw.mymvp.BuildConfig;

/**
 * Created by Administrator on 2017/7/15.
 */

public class IpConfig {
    //服务器地址
    private static String getBaseUrl() {
        String IP = "";
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
        } else {
            IP = "";//服务器正式地址
        }
        return IP;
    }
    public static final String Login = getBaseUrl() + "";
}
