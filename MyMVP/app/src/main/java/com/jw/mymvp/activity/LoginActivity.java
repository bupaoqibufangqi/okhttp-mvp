package com.jw.mymvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jw.mymvp.R;
import com.jw.mymvp.application.MyApplication;
import com.jw.mymvp.base.BaseActivity;
import com.jw.mymvp.bean.UserInfo;
import com.jw.mymvp.config.IpConfig;
import com.jw.mymvp.mvp.presenter.LoginPresenter;
import com.jw.mymvp.mvp.view.ILoginView;
import com.jw.mymvp.util.MyToast;
import com.jw.mymvp.util.base.SharedOperation;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.cb_is_remember_password)
    CheckBox cbIsRememberPassword;
    @Bind(R.id.btnReg)
    Button btnReg;
    @Bind(R.id.btnForgetPass)
    Button btnForgetPass;
    @Bind(R.id.bt_login)
    Button btLogin;
    private UserInfo user_init = new UserInfo();
    private LoginPresenter loginPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        activityName = getString(R.string.login_title);
        super.onCreate(savedInstanceState);
        // 设置渲染视图View
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        loginPresenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.bt_login)
    public void onViewClicked() {
//        login();
        toMainActivity();
    }

    @Override
    public void showLoading() {
        showWaitDialog("正在登录...");
    }

    @Override
    public void dimissLoading() {
        dismissWaitDialog();
    }

    @Override
    public void loginSuccess(String result) {

    }

    @Override
    public void loginEmpty() {

    }

    @Override
    public void netError() {
        MyToast.showToast(MyApplication.getContext(),getString(R.string.login_net_error));
    }

    @Override
    public void getUserInfo() {
        if (SharedOperation.isKeyValue(mcontext, "userInfo")) {
            try {
                user_init = getBaseUserInfo();
                etUsername.setText(user_init.getLoginName());
                etPassword.setText(user_init.getLoginPwd());
                cbIsRememberPassword.setChecked(user_init.isRememberPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfo.setRememberPassword(cbIsRememberPassword.isChecked());
        String user = new Gson().toJson(userInfo);
        SharedOperation.putValue(mcontext, "userInfo", user);
    }

    @Override
    public boolean checkInput() {
        final String userid = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(userid) || TextUtils.isEmpty(password)) {
            MyToast.showToast(LoginActivity.this, getString(R.string.login_empyt_input));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void login() {
        HashMap<String, String> params = new HashMap<>();
        loginPresenter.login(IpConfig.Login, params);
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
