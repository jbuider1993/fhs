package com.fhs.base.api.ucenter.rpc;

import com.fhs.base.api.config.FeignConfiguration;
import com.fhs.basics.vo.WordbookVO;
import com.fhs.core.result.HttpResult;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;


/**
 * 字典公共服务
 */
@FeignClient(value = "system", configuration = FeignConfiguration.class,primary = false)
public interface FeignWordBookApiService {

    /**
     * 根据字典编码获取字典信息
     * @param wordBookGroupCode 字典编码
     * @return HttpResult 角色数据权限
     */
    @RequestLine("GET /api/com.fhs.base.api.ucenter.rpc.FeignWordBookApiService/getWordBookList?wordBookGroupCode={wordBookGroupCode}")
    HttpResult<List<WordbookVO>> getWordBookList(@Param("wordBookGroupCode") String wordBookGroupCode);
}
