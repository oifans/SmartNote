package com.weibo.lbzone.smartnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.bmob.v3.Bmob;

/**
 * Created by LB on 2017/4/18.
 * 基础Activity
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Bmob
        Bmob.initialize(this,Constant.BmobAppId);
        findViewById();
    }

    public void findViewById(){};
}
