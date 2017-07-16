package com.jw.mymvp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.text.TextUtils;

import com.jw.mymvp.util.base.Utils;
import com.jw.mymvp.util.base.CrashHandler;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Stack;

public class MyApplication extends Application {
	//配合UIUtils使用
	private static Context context;
	private static int mainThreadId;
	private static Handler mainThreadHandler;
	public String SSESION_ID = "";//全局使用的SSESION_ID
	// 使用简单的单例模式
	private static MyApplication instance = null;

	//引入自定义异常处理器
	private String logdir = "/ghinfo/crash_log/";
	private String appdir = "/ghinfo/app/";
	private String LogPath;//CrashLog输出的完整路径目录
	private String appPath;//下载app的保存路径

	public String getAppPath(){
		File file = new File(appPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return appPath;
	}

	public static MyApplication getInstance() {
		if (instance == null) {
			instance = new MyApplication();
		}
		return instance;
	}
	// 设置存放Activity堆栈
	private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
	// 程序开始入口
	public void onCreate() {
		super.onCreate();
		instance = this;
		this.context = this;
		this.mainThreadId = android.os.Process.myTid();
		this.mainThreadHandler = new Handler();
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);try {ndef.addDataType("*/*");} catch (IntentFilter.MalformedMimeTypeException e) {throw new RuntimeException("fail", e);}

		initDir();//初始化本地数据保存的路径

		CrashHandler crashHandler = CrashHandler.getInstance();// 全局捕捉异常工具类
		crashHandler.init(getApplicationContext(), mainThreadHandler, LogPath);
	}

	private void initDir() {
		if (!TextUtils.isEmpty(Utils.getSDPath())){
			LogPath = Utils.getSDPath()  + logdir;
			appPath = Utils.getSDPath() + appdir;
		}else {
			LogPath = Utils.getFilesDirPath(this) + logdir;
			appPath = Utils.getFilesDirPath(this) + appdir;
		}
	}

	/******************************************* Application中存放的Activity操作（压栈/出栈）API（开始） *****************************************/
	// 将Activity压入Application栈
	// task： 将要压入栈的Activity对象
	public void pushTask(WeakReference<Activity> task) {
		activitys.push(task);
	}
	// 将传入的Activity对象从栈中移除
	public void removeTask(WeakReference<Activity> task) {
		if (!task.get().isFinishing()){
			activitys.remove(task);
		}
	}

	// 根据指定位置从栈中移除Activity
	// taskIndex： Activity栈索引
	public void removeTask(int taskIndex) {
		if (activitys.size() > taskIndex)
			activitys.remove(taskIndex);
	}

	// 将栈中Activity移除至栈顶
	public void removeToTop() {
		int end = activitys.size();
		int start = 1;
		for (int i = end - 1; i >= start; i--) {
			if (!activitys.get(i).get().isFinishing()) {
				activitys.get(i).get().finish();
			}
		}
	}

	// 移除全部（用于整个应用退出）
	public void removeAll() {
		for (WeakReference<Activity> task : activitys) {
			try {
				if (!task.get().isFinishing()) {
					task.get().finish();
				}
			} catch (Exception e) {
			}
		}
	}
	/******************************************* Application中存放的Activity操作（压栈/出栈）API（结束） *****************************************/

	/**********************************UIUtils调用到的方法（开始）***************************************************/
	public static Context getContext() {
		return context;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

	public static Handler getMainThreadHandler() {
		return mainThreadHandler;
	}
	/**********************************UIUtils调用到的方法(结尾)***************************************************/
}
