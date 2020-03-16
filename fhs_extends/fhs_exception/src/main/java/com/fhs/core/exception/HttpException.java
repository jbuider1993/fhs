package com.fhs.core.exception;

/**
 * 其他的异常http状态码都是200，通过httpresult中的code来判断状态
 * 而此异常抛出后，http状态码为自定义状态码
 * @Filename: HttpException.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class HttpException extends RuntimeException {

    /**
     *serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     *后台系统错误
     */
    public static final int SYSTEM_ERROR = 500;


    /**
     *没有权限
     */
    public static final int NO_PERMISSION = 403;

    /**
     *成功
     */
    public static final int SUCCESS = 200;

    /**
     *http状态码
     */
    private int httpCode;

    public HttpException(String message,int httpCode)
    {
        super(message);
    }

    public int getHttpCode()
    {
        return httpCode;
    }

    public void setHttpCode(int httpCode)
    {
        this.httpCode = httpCode;
    }

}
