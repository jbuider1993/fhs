package com.fhs.basics.api.rpc;

import com.fhs.basics.form.GetSingleFrontUserForm;
import com.fhs.basics.vo.UcenterFrontUserVO;
import com.fhs.core.feign.config.FeignConfiguration;
import com.fhs.core.result.HttpResult;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 用户权限service
 * @author  zhangqiang
 * @version  [版本号, 2018/08/09 9:26:11]
 * @versio  1.0
 * Copyright (c) 2017 All Rights Reserved.
 **/
@FeignClient(value = "system", configuration= FeignConfiguration.class,primary = false)
public interface FeignFrontUserApiService {

       /**
     * @desc 获取单个前端用户信息
     * @param getSingleFrontUserForm 用户accessToken的form对象
     * @return 用户信息
     */
    @RequestLine("POST /api/FeignFrontUserApiService/getSingleFrontUser")
    HttpResult<UcenterFrontUserVO> getSingleFrontUser(@RequestBody GetSingleFrontUserForm getSingleFrontUserForm);

    /**
     * 修改用户信息
     * @param frontUserVo 用户vo
     * @return 是否修改成功
     */
    @RequestLine("POST /api/FeignFrontUserApiService/update")
    HttpResult<Boolean> update(@RequestBody UcenterFrontUserVO frontUserVo);

    /**
     * 添加用户信息
     * @param frontUserVo 用户vo
     * @return 是否添加成功
     */
    @RequestLine("POST /api/FeignFrontUserApiService/add")
    HttpResult<Boolean> add(@RequestBody UcenterFrontUserVO frontUserVo);

    /**
     * 使用给定的参数当做参数过滤用户
     * @param frontUserVo 用户vo
     * @return 用户vo
     */
    @RequestLine("POST /api/FeignFrontUserApiService/find")
    HttpResult<UcenterFrontUserVO> find(@RequestBody UcenterFrontUserVO frontUserVo);

}