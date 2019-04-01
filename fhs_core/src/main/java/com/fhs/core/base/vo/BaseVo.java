package com.fhs.core.base.vo;

import com.fhs.core.base.bean.BaseObject;
import com.fhs.core.result.HttpResult;

/**
 * @Description:VO基类
 * @Version: 1.0
 * @Author: duanwei
 * @Email: duanwei@sxpartner.com 陕西小伙伴网络科技有限公司  Copyright (c) 2017 All Rights Reserved.
 */
public class BaseVo<T extends  BaseVo> extends BaseObject<T>
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回成功的result
     * @return HttpResult
     */
    public HttpResult success() {
        return HttpResult.success(this);
    }

}
