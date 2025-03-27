package com.tencent.subject.application.util;

import com.tencent.subject.application.context.LoginContextHolder;

/**
 * 用户登陆util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
