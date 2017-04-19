package com.weibo.lbzone.smartnote.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LB on 2017/4/19.
 * 数据验证工具类
 */

public class ValidateUtil {

    /**
     * 是否Email地址
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 是否是手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1[3|4|5|7|8][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
