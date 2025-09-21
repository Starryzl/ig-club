package com.tencent.circle.server.util;


import com.tencent.circle.server.config.context.LoginContextHolder;

/**
 * 用户登陆util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
