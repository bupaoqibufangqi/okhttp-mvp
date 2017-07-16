package com.jw.mymvp.base;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jw.mymvp.R;
import com.jw.mymvp.application.MyApplication;
import com.jw.mymvp.bean.UserInfo;
import com.jw.mymvp.util.base.SharedOperation;
import com.jw.mymvp.util.base.WaitDialog;

import java.lang.ref.WeakReference;


public class BaseActivity extends AppCompatActivity {
    public Context mcontext;
    // 整个应用Applicaiton
    public MyApplication myApplication = null;
    // 当前Activity的弱引用，防止内存泄露
    private WeakReference<Activity> context = null;
    // 页面标题名称
    public String activityName = "";
    // 是否隐藏标题的返回按钮
    public boolean hideBackBtn;
    // 标题左侧和右侧的图片控件
    public ImageView iv_left, iv_right;
    //标题右侧的文字控件
    public TextView tv_right;
    //标题右侧的父控件
    public LinearLayout ll_right;
    public Button bt_reback;
    // 弹出加载页面
    private WaitDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarLayout(R.layout.item_base_title, this, activityName);
        myApplication = MyApplication.getInstance();
        this.mcontext = this;
        // 将当前Activity压入栈
        context = new WeakReference<Activity>(this);
        myApplication.pushTask(context);
    }

    @Override
    protected void onDestroy() {
        myApplication.removeTask(context);// 将activty从堆栈中去除
        super.onDestroy();
    }

    // 设置自定义ActionBar的布局
    @SuppressLint("NewApi")
    public void setActionBarLayout(int layoutId, Context mContext,
                                   String activityName) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // 如果设置有ActionBar时
        if (null != actionBar) {
            // 设置隐藏默认的ActionBar
            actionBar.setDisplayShowHomeEnabled(false);
            // 设置显示自定义的ActionBar
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            LayoutInflater inflator = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 获取自定义的ActionBar
            View v = inflator.inflate(layoutId, new LinearLayout(mContext),
                    false);
            android.support.v7.app.ActionBar.LayoutParams layout = new android.support.v7.app.ActionBar.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, layout);
            TextView activitytitle = (TextView) v
                    .findViewById(R.id.tv_title);
            activitytitle.setText(activityName);
            bt_reback = (Button) v.findViewById(R.id.bt_reback);
            iv_left = (ImageView) v.findViewById(R.id.iv_left);
            iv_right = (ImageView) v.findViewById(R.id.iv_right);
            tv_right = (TextView) v.findViewById(R.id.tv_right);
            ll_right = (LinearLayout) v.findViewById(R.id.ll_right);
            if (hideBackBtn) {
                bt_reback.setVisibility(View.GONE);
            } else {
                bt_reback.setOnClickListener(reBackClickListener);
            }
        }
    }

    // 点击返回事件
    private View.OnClickListener reBackClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            pageReBack();
        }

    };

    // 按手机上的返回键
    @Override
    public void onBackPressed() {
        pageReBack();
    }

    // 从堆栈删除activity并返回
    private void pageReBack() {
        finish();
        // 关闭窗体动画显示
        overridePendingTransition(0, R.anim.slide_right_out);
    }

    /**
     * 等待窗口一
     *
     * @param message
     * @return
     */
    public WaitDialog showWaitDialog(String message) {
        WaitDialog.Builder waitDialogBuilder = new WaitDialog.Builder(mcontext);
        waitDialogBuilder.setMessage(message);
        waitDialogBuilder.setLoading(true);
        WaitDialog waitDialog = waitDialogBuilder.create();
        waitDialog.show();
        return waitDialog;
    }

    public void dismissWaitDialog() {
        waitDialog.cancel();
    }


    // 获取SharedPreferences保存的用户信息
    public UserInfo getBaseUserInfo() {
        UserInfo userInfo = new UserInfo();
        if (SharedOperation.isKeyValue(mcontext, "userInfo")) {
            try {
                String userString = SharedOperation.getValue(mcontext,
                        "userInfo", "");
                Gson mGson = new Gson();
                userInfo = mGson.fromJson(userString, UserInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }
}