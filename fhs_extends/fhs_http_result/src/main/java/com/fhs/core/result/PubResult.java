package com.fhs.core.result;

/**
 * 公共的result
 * @Filename: PubResult.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public enum PubResult implements BaseResult
{
    /**
     *成功
     */
    SUCCESS(200,"成功"),

    /**
     *token超时
     */
    TOKEN_TIMEOUT(402,"token过期请重新获取"),

    /**
     *签名校验失败
     */
    SIGN_ERROR(401,"签名校验失败"),

    /**
     *渠道商id错误
     */
    CHANNEL_ID_ERROR(405,"渠道商id错误"),

    /**
     * 权限不足
     */
    PARAM_ERROR(400,"参数错误"),

    /**
     * 权限不足
     */
    NO_PERMISSION(403,"权限不足"),

    /**
     * 权限不足
     */
    NO_FIND(404,"请求的资源不存在"),

    /**
     * 主键冲突
     */
    PRIMARY_KEY_CONFLICT(409,"主键冲突"),
    /**
     *后台错误
     */
    SYSTEM_ERROR(500,"后台错误"),

    /**
     *后台繁忙
     */
    SYSTEM_BUSY(504,"后台错误"),

    /**
     *超时
     */
    TIMEOUT_ERROR(503,"系统处理超时，您可以通过相关查询接口查询业务是否执行成功"),

    /**
     *请求时间和处理时间差距过大
     */
    REQTIME_TO_OLD(406,"请求时间和处理时间差距过大"),

    /**
     * 签名过期，请重新请求
     */
    TICKET_TIME_OUT(408,"ticket过期，您在一个小时内未调用过ticket获取接口，请您调用获取新ticket重试");



    private int code;

    private String message;

    private PubResult(int code, String message)
    {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode()
    {
        return code;
    }

    @Override
    public String getMessage()
    {
        return message;
    }


}
