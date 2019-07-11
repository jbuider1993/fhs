package com.fhs.system.api;

import com.fhs.core.result.HttpResult;
import com.fhs.feignConfig.FeignConfiguration;
import com.fhs.system.bean.LogAdminOperatorLogVo;
import com.fhs.system.bean.WordbookVO;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 字典公共服务
 */
@FeignClient(value = "system", configuration = FeignConfiguration.class)
public interface FeignWordBookApiService {

    /**
     * 根据字典编码获取字典信息
     * @param wordBookGroupCode 字典编码
     * @return HttpResult 角色数据权限
     */
    @RequestLine("GET /api/wordbook/getWordBookList?wordBookGroupCode={wordBookGroupCode}")
    HttpResult<List<WordbookVO>>  getWordBookList(@Param("wordBookGroupCode") String wordBookGroupCode);
}
