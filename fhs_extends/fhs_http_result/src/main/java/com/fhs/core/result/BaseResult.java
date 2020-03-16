package com.fhs.core.result;

import java.io.Serializable;

/**
 * 返回枚举基础接口
 * @Filename: BaseResult.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public interface BaseResult extends Serializable
{
    /**
     *serialVersionUID
     */
    static final long serialVersionUID = 1L;

    /**
     * 获取code
     * @return the code
     */
    int getCode();

    /**
     * 获取code对应的message
     * @return the message
     */
    String getMessage();

    /**
     * 把本身转换为一个空的result
     * @return HttpResult
     */
    public default HttpResult asResult() {
        return HttpResult.otherResult(this);
    }

    /**
     * 把本身转换为一个空的result
     * @return HttpResult
     */
    public default HttpResult asResult(Class cls) {
        return HttpResult.otherResult(cls, this);
    }

    /**
     * 把本身转换为一个空的result
     * @param data 给前台返回的数据
     * @return HttpResult
     */
    public default HttpResult asResult(Object data) {
        return HttpResult.otherResult(data,this);
    }

}
