package com.tencent.core.utils;

import com.tencent.core.entity.Context;

/**
 * 获取全局上下文的配置工具
 *
 * @author loser
 * @date 2023/9/4
 */
public class ConfigUtils {

    private static Context ctx = new Context();

    public static Context getContext() {
        return ctx;
    }

    public static void reSetContext(Context context) {
        ctx = context;
    }

}
