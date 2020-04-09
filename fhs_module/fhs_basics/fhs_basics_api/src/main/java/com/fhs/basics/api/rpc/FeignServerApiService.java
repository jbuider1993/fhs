package com.fhs.basics.api.rpc;

import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.basics.vo.SettMsMenuServerVO;
import com.fhs.core.feign.config.FeignConfiguration;
import com.fhs.core.result.HttpResult;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 服务器公共服务
 */
@FeignClient(value = "basics", configuration = FeignConfiguration.class,primary = false)
public interface FeignServerApiService {

    /**
     * 根据服务id获取服务对象
     * @param id 服务id
     * @return HttpResult 服务对象
     */
    @RequestLine("GET /api/com.fhs.basics.api.rpc.FeignServerApiService/getMenuServeById?id={id}")
    HttpResult<SettMsMenuServerVO> getMenuServeById(@Param("id") String id);



}
