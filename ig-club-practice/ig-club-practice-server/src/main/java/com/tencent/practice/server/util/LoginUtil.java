package com.tencent.practice.server.util;


import com.tencent.practice.server.config.context.LoginContextHolder;

/**
 * 用户登陆util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
