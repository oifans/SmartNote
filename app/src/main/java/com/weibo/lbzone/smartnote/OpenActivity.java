package com.weibo.lbzone.smartnote;

import android.content.Context;
import android.content.Intent;

import com.weibo.lbzone.smartnote.activity.LoginActivity;
import com.weibo.lbzone.smartnote.activity.MainActivity;
import com.weibo.lbzone.smartnote.activity.RegisterActivity;

/**
 * Created by LB on 2017/4/18.
 * 所有打开Activity都在此类
 */

public class OpenActivity {

    /**
     * 打开注册界面
     *
     * @param context
     */
    public static void ToRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    /**
     * 打开首页
     *
     * @param context
     */
    public static void ToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 打开登录页面
     * @param context
     */
    public static void ToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
