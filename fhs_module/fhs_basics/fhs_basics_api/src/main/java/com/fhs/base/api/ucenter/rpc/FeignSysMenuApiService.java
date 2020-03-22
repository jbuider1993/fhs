package com.fhs.base.api.ucenter.rpc;

import com.fhs.base.api.config.FeignConfiguration;
import com.fhs.basics.vo.SysMenuVO;
import com.fhs.core.result.HttpResult;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

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
@FeignClient(value = "system", configuration = FeignConfiguration.class,primary = false)
public interface FeignSysMenuApiService {

    /**
     * 查询id,name,namespace列表
     */
    @RequestLine("GET /api/com.fhs.base.api.ucenter.rpc.FeignSysMenuApiService/findIdAndNameAndNamespaceList")
    HttpResult<List<SysMenuVO>> findIdAndNameAndNamespaceList();

}
