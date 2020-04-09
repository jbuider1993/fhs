package com.fhs.flow.api.rpc;

import com.fhs.basics.vo.ServiceWordbookVO;
import com.fhs.basics.vo.SettMsMenuServerVO;
import com.fhs.core.feign.config.FeignConfiguration;
import com.fhs.core.result.HttpResult;
import com.fhs.flow.vo.ReSubmitVO;
import com.fhs.flow.vo.StartProcessInstanceVO;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * 工作流服务
 */
@FeignClient(value = "flow", configuration = FeignConfiguration.class,primary = false)
public interface FeignWorkFlowApiService {


    /**
     * 启动一个流程
     * @param startProcessInstance 启动流程参数
     * @return instanceid
     */
    @RequestLine("POST /api/com.fhs.flow.api.rpc.FeignWorkFlowApiService/startProcessInstanceForApi")
    HttpResult<String> startProcessInstanceForApi(@RequestBody StartProcessInstanceVO startProcessInstance);

    /**
     * 根据服务id获取服务对象
     * @param reSubmitVO 重新提交的vo
     * @return HttpResult
     */
    @RequestLine("POST /api/com.fhs.flow.api.rpc.FeignWorkFlowApiService/reSubmit")
    HttpResult reSubmit(@RequestBody ReSubmitVO reSubmitVO);


}
