package com.fhs.ucenter.api.service;

import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

/**
 * 机构管理rest接口
 */
@FeignClient(value = "system", configuration=FeignConfiguration.class)
public interface UcenterMsOrganizationApiService {

    /**
     * 查询用户住结构
     * @param userId 用户id
     * @return 结构对象
     */
    @RequestLine("GET /api/uMOrganization/getMainGroup?userId={userId}")
    HttpResult<Map<String,Object>> getMainGroup(@Param("userId") String userId);

    /**
     * 查询组织机构
     * @param id 组织机构名称
     * @return 结构对象
     */
    @RequestLine("GET /api/uMOrganization/getGroupById?id={id}")
    HttpResult<Map<String,Object>> getGroupById(@Param("id") String id);

}
