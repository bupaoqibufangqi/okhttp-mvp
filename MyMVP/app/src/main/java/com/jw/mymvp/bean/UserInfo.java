package com.jw.mymvp.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    // 用户id
    private String LoginID;
    // 登陆名
    private String LoginName;
    // 密码
    private String LoginPwd;
    // 员工编号
    private String EMPCODE;
    // 员工名
    private String EMPNAME;
    // 所在组织架构编号
    private String ORGCODE;
    // 组织架构名称
    private String ORGNAME;
    // 所在部门编号
    private String DEPCODE;
    // 部门负责人
    private String DEPHead;
    // 所在部门名称
    private String DEPNAME;
    // 职位编号
    private String PositionCode;
    // 职位名称
    private String PositionName;
    //传感器服务器地址前缀
    private String SensorServer;
    //流媒体端口
    private String MediaPort;
    //流媒体IP
    private String MediaIP;
    //是否记住用户密码，额外添加的字段
    private boolean isRememberPassword;
    //上次下载app的时间
    private long lastDownLoadTime;

    public String getLoginID() {
        return LoginID;
    }

    public void setLoginID(String loginID) {
        LoginID = loginID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        LoginPwd = loginPwd;
    }

    public String getEMPCODE() {
        return EMPCODE;
    }

    public void setEMPCODE(String eMPCODE) {
        EMPCODE = eMPCODE;
    }

    public String getEMPNAME() {
        return EMPNAME;
    }

    public void setEMPNAME(String eMPNAME) {
        EMPNAME = eMPNAME;
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public void setORGCODE(String oRGCODE) {
        ORGCODE = oRGCODE;
    }

    public String getORGNAME() {
        return ORGNAME;
    }

    public void setORGNAME(String oRGNAME) {
        ORGNAME = oRGNAME;
    }

    public String getDEPCODE() {
        return DEPCODE;
    }

    public void setDEPCODE(String dEPCODE) {
        DEPCODE = dEPCODE;
    }

    public String getDEPHead() {
        return DEPHead;
    }

    public void setDEPHead(String dEPHead) {
        DEPHead = dEPHead;
    }

    public String getDEPNAME() {
        return DEPNAME;
    }

    public void setDEPNAME(String dEPNAME) {
        DEPNAME = dEPNAME;
    }

    public String getPositionCode() {
        return PositionCode;
    }

    public void setPositionCode(String positionCode) {
        PositionCode = positionCode;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getSensorServer() {
        return SensorServer;
    }

    public void setSensorServer(String sensorServer) {
        SensorServer = sensorServer;
    }

    public boolean isRememberPassword() {
        return isRememberPassword;
    }

    public void setRememberPassword(boolean rememberPassword) {
        isRememberPassword = rememberPassword;
    }

    public String getMediaPort() {
        return MediaPort;
    }

    public void setMediaPort(String mediaPort) {
        MediaPort = mediaPort;
    }

    public String getMediaIP() {
        return MediaIP;
    }

    public void setMediaIP(String mediaIP) {
        MediaIP = mediaIP;
    }

    public long getLastDownLoadTime() {
        return lastDownLoadTime;
    }

    public void setLastDownLoadTime(long lastDownLoadTime) {
        this.lastDownLoadTime = lastDownLoadTime;
    }
}
