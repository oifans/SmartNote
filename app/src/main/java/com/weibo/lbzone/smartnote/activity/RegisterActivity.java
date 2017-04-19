package com.weibo.lbzone.smartnote.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weibo.lbzone.smartnote.BaseActivity;
import com.weibo.lbzone.smartnote.Constant;
import com.weibo.lbzone.smartnote.OpenActivity;
import com.weibo.lbzone.smartnote.R;
import com.weibo.lbzone.smartnote.bean.User;
import com.weibo.lbzone.smartnote.util.ToastUtils;
import com.weibo.lbzone.smartnote.util.ValidateUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by LB on 2017/4/18.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtSendCode;
    private Button mBtRegister;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private EditText mEtIdentifyingCode;
    private TextView mTvHint;
    private String TAG = "RegisterActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById();
        mBtRegister.setOnClickListener(this);
        mBtSendCode.setOnClickListener(this);
    }

    @Override
    public void findViewById() {
        mBtSendCode = (Button) findViewById(R.id.bt_sendcode);
        mBtRegister = (Button) findViewById(R.id.bt_register);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvHint = (TextView) findViewById(R.id.tv_hint);
        mEtIdentifyingCode = (EditText) findViewById(R.id.et_identifyingcode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sendcode:
                sendCode();
                break;
            case R.id.bt_register:
                register();
                break;
        }
    }

    /**
     * 注册用户
     */
    private void register() {
        final User user = new User();
        final String passWord, userName, identifyingCode;
        userName = mEtUserName.getText().toString();
        passWord = mEtPassword.getText().toString();
        identifyingCode = mEtIdentifyingCode.getText().toString();

        if (ValidateUtil.isMobileNO(userName)) {
            if (Constant.isDebug) {
                user.setUsername(userName);
                user.setMobilePhoneNumber(userName);
                user.setMobilePhoneNumberVerified(true);
                user.setPassword(passWord);
                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.registerSuccess), Toast.LENGTH_SHORT).show();
                            OpenActivity.ToLoginActivity(RegisterActivity.this);
                        }
                    }
                });
                return;
            }
            BmobSMS.verifySmsCode(userName, identifyingCode, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        user.setUsername(userName);
                        user.setMobilePhoneNumber(userName);
                        user.setMobilePhoneNumberVerified(true);
                        user.setPassword(passWord);
                        user.signUp(new SaveListener<User>() {
                            @Override
                            public void done(User user, BmobException e) {
                                if (e == null) {
                                    ToastUtils.showShortToast(RegisterActivity.this, getResources().getString(R.string.registerSuccess));
                                    OpenActivity.ToMainActivity(RegisterActivity.this);
                                }
                            }
                        });
                    } else {
                        Log.i(TAG, "验证失败：code =" + e.getErrorCode() + ",msg = " + e.getLocalizedMessage());
                    }
                }
            });
        } else {
            ToastUtils.showShortToast(this, getResources().getString(R.string.phoneNumError));
            mTvHint.setVisibility(View.VISIBLE);
            mTvHint.setText(getResources().getString(R.string.phoneNumError));
        }
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
        String phoneNum;
        phoneNum = mEtUserName.getText().toString();
        if (ValidateUtil.isMobileNO(phoneNum)) {
            if (Constant.isDebug) {
                ToastUtils.showShortToast(this, "验证码:1111");
                return;
            }
            BmobSMS.requestSMSCode(phoneNum, Constant.BmobYzmName, new QueryListener<Integer>() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if (e == null) {
                                mBtSendCode.setText("验证码已发送");
                            }
                        }
                    }
            );
        }
    }

}
