package com.tencent.interview.server.util;


import com.tencent.interview.server.config.context.LoginContextHolder;

/**
 * 用户登陆util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
