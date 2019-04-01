package com.fhs.system.api;

import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import com.fhs.system.bean.LogAdminOperatorLogVo;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Filename: FeignlogAdminOperatorLogApiService.java
 * @Description: 操作日志api服务
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@FeignClient(value = "system", configuration = FeignConfiguration.class)
public interface FeignlogAdminOperatorLogApiService {

    /**
     * 添加操作日志
     */
    @RequestLine("POST /api/logAdminOperatorLog/addLogAdminOperatorLog")
    HttpResult<Integer> addLogAdminOperatorLog(@RequestBody LogAdminOperatorLogVo logAdminOperatorLog);
}
