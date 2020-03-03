package com.fhs.demo.service;

import com.fhs.feignConfig.FeignConfiguration;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.HashMap;

/**
 * @Description: 测试调用Php提供的服务
 * @author  zhangqiang
 * @version  [版本号, 2018/08/09 9:26:11]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 **/
@FeignClient(value = "testPhp", configuration=FeignConfiguration.class)
public interface SideCarApiService {
    @RequestLine("POST /hello.php")
    HashMap<String,String> sayHello();
}