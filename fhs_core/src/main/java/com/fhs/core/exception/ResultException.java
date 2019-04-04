package com.fhs.core.exception;

import com.fhs.core.result.HttpResult;


/**
 * 由service层直接返回一个httpresult到前段
 */
public class ResultException extends YZBNotLogException{

    /**
     * 给前段返回的结果
     */
    private HttpResult<?> httpResult;

    public ResultException(HttpResult<?> httpResult) {
        this.httpResult = httpResult;
    }

    public HttpResult<?> getHttpResult(){
        return httpResult;
    }
}
