package com.tencent.subject.common.util;


import com.tencent.subject.common.context.LoginContextHolder;

/**
 * 用户登陆util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
