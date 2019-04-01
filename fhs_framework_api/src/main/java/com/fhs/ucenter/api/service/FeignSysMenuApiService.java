package com.fhs.ucenter.api.service;

import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import com.fhs.ucenter.api.vo.SysMenuVo;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * 系统菜单api服务
 *
 * @Filename: FeignSysMenuApiService.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@FeignClient(value = "system", configuration = FeignConfiguration.class)
public interface FeignSysMenuApiService {

    /**
     * 查询id,name,namespace列表
     */
    @RequestLine("GET /api/sysMenu/findIdAndNameAndNamespaceList")
    HttpResult<List<SysMenuVo>> findIdAndNameAndNamespaceList();

}
