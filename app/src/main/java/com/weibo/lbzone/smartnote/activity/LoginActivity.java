package com.weibo.lbzone.smartnote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weibo.lbzone.smartnote.BaseActivity;
import com.weibo.lbzone.smartnote.OpenActivity;
import com.weibo.lbzone.smartnote.R;
import com.weibo.lbzone.smartnote.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by LB on 2017/4/18.
 * 登录Activity
 */

public class LoginActivity extends BaseActivity {

    private Button mBtLogin;
    private EditText mEtUserName;
    private EditText mEtPassWord;
    private TextView mTvRegister;
    private TextView mTvFindPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();

        //登录系统
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String phoneNum = mEtUserName.getText().toString();
                String password = mEtPassWord.getText().toString();
                user.loginByAccount(phoneNum, password, new LogInListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(user != null){
                            OpenActivity.ToMainActivity(LoginActivity.this);
                        }
                    }
                });

            }
        });

        //进入注册界面
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity.ToRegisterActivity(LoginActivity.this);
            }
        });
    }

    @Override
    public void findViewById() {
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassWord = (EditText) findViewById(R.id.et_password);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvFindPassword = (TextView) findViewById(R.id.tv_find);
        mBtLogin = (Button) findViewById(R.id.bt_login);
    }
}
