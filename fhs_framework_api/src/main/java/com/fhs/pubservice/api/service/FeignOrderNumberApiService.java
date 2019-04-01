package com.fhs.pubservice.api.service;

import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 订单号生成服务
 */
@FeignClient(value = "system", configuration = FeignConfiguration.class)
public interface FeignOrderNumberApiService {

    /**
     * 获取一个订单号
     * @param type 订单号类型
     * @return 订单号
     */
    @RequestLine("GET api/orderNumber/getOrderNumber?type={type}")
    HttpResult<String> getOrderNumber(@Param("type")String type);
}
