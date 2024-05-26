package com.roundlun.iot.utils;

import com.roundlun.iot.domain.model.LoginUser;

public class BaseUserInfo {
    /**
     * 使用线程变量存储当前用户信息
     */
    private final static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void set(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
