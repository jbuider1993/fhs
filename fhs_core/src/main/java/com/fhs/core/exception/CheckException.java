package com.fhs.core.exception;

import com.fhs.core.result.HttpResult;


/**
 * @Description:携带一个httpResult返回
 * @Version: 1.0
 * @Author: duanwei
 * @Email: duanwei@sxpartner.com
 * 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class CheckException extends YZBNotLogException
{

    private static final long serialVersionUID = 1L;

    public CheckException(HttpResult result)
    {
        super(result.getMessage());
        this.result = result;
    }

    public CheckException()
    {

    }

    protected HttpResult result;

    public HttpResult getResult()
    {
        return result;
    }

    public void setResult(HttpResult result)
    {
        this.result = result;
    }
}
