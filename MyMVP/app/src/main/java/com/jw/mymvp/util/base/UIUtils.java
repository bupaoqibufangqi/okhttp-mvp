package com.jw.mymvp.util.base;


import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.jw.mymvp.application.MyApplication;

public class UIUtils {
	//主线程
	public static void runInMainThread(Runnable runnable){
		if(isRunInMainThread()){
			runnable.run();
		}else{
			getHandler().post(runnable);
		}
	}
	public static boolean isRunInMainThread(){
		return android.os.Process.myTid() == getMainThreadId();
	}
	public static void removeCallBack(Runnable runnableTask) {
		getHandler().removeCallbacks(runnableTask);
	}
	public static void postDelayed(Runnable runnableTask, int delayMillis) {
		getHandler().postDelayed(runnableTask, delayMillis);
	}
	public static int getMainThreadId(){
		return MyApplication.getMainThreadId();
	}
	//主线程handler
	public static Handler getHandler(){
		return MyApplication.getMainThreadHandler();
	}
	//获得context
	public static Context getContext(){

		return MyApplication.getContext();
	}

	public static View getXmlView(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}

	/**
	 * 1dp---1px;
	 * 1dp---0.75px;
	 * 1dp---0.5px;
	 * ....
	 *
	 * @param dp
	 * @return
	 */
	public static int dp2px(int dp) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dp * density + 0.5);
	}

	public static int px2dp(int px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5);
	}
}
