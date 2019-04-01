package com.fhs.common.utils;

/**
 * 记录线程的业务key
 * @Filename: ThreadKey.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class ThreadKey
{
    /**
     *业务 KEY
     */
    public static final ThreadLocal<String> BUS_KEY = new ThreadLocal<String>();
}
