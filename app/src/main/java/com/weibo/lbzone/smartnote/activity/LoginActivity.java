package com.weibo.lbzone.smartnote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weibo.lbzone.smartnote.BaseActivity;
import com.weibo.lbzone.smartnote.OpenActivity;
import com.weibo.lbzone.smartnote.R;
import com.weibo.lbzone.smartnote.bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by LB on 2017/4/18.
 * 登录Activity
 */

public class LoginActivity extends BaseActivity {

    private Button mBtLogin;
    private Button mBtRegister;
    private EditText mEtUserName;
    private EditText mEtPassWord;

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
                user.setUsername(mEtUserName.getText().toString());
                user.setPassword(mEtPassWord.getText().toString());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                            user = BmobUser.getCurrentUser(User.class);
                        } else {

                        }
                    }
                });

            }
        });

        //进入注册界面
        mBtRegister.setOnClickListener(new View.OnClickListener() {
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
        mBtRegister = (Button) findViewById(R.id.bt_register);
        mBtLogin = (Button) findViewById(R.id.bt_login);
    }
}
