package com.fhs.basics.api.rpc;

import com.fhs.core.feign.config.FeignConfiguration;
import com.fhs.core.result.HttpResult;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

/**
 * 机构管理rest接口
 */
@FeignClient(value = "basics", configuration= FeignConfiguration.class,primary = false)
public interface FeignOrganizationApiService {

    /**
     * 查询用户住结构
     * @param userId 用户id
     * @return 结构对象
     */
    @RequestLine("GET /api/FeignOrganizationApiService/getMainGroup?userId={userId}")
    HttpResult<Map<String,Object>> getMainGroup(@Param("userId") String userId);

    /**
     * 查询组织机构
     * @param id 组织机构名称
     * @return 结构对象
     */
    @RequestLine("GET /api/FeignOrganizationApiService/getGroupById?id={id}")
    HttpResult<Map<String,Object>> getGroupById(@Param("id") String id);

}
