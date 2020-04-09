package com.fhs.flow.pagex.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.EMap;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.exception.NotPremissionException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.feign.autowired.annotation.AutowiredFhs;
import com.fhs.core.result.HttpResult;
import com.fhs.flow.api.rpc.FeignWorkFlowApiService;
import com.fhs.flow.constant.FlowConstant;
import com.fhs.flow.vo.ReSubmitVO;
import com.fhs.flow.vo.StartProcessInstanceVO;
import com.fhs.logger.anno.LogDesc;
import com.fhs.pagex.common.ExcelExportTools;
import com.fhs.pagex.controller.PageXBaseController;
import com.fhs.pagex.service.PagexDataService;
import com.fhs.pagex.vo.PageXTreeVO;
import com.fhs.pagex.vo.PagexListSettVO;
import com.fhs.pagex.vo.TreeVO;
import com.mybatis.jpa.context.DataPermissonContext;
import com.mybatis.jpa.context.MultiTenancyContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * pagex的控制器
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex
 * @ClassName: PageXPubAction
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 19:44
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 19:44
 * @Version: 1.0
 */
@RestController
@AutowiredFhs
@RequestMapping("/ms/x/flow")
public class PageXMsFlowPubController extends PageXBaseController {

    @AutowiredFhs
    private FeignWorkFlowApiService feignWorkFlowApiService;

    /**
     * 添加-后台只做重复校验，不做参数格式校验
     *
     * @param namespace namespace
     */
    @RequestMapping("{namespace}/add")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult<Boolean> add(@PathVariable("namespace") String namespace, HttpServletRequest request, HttpServletResponse response) {
        checkPermiessAndNamespace(namespace, "add");
        EMap<String, Object> paramMap = super.getParameterMap();
        UcenterMsUserVO user = getSessionUser(request);
        paramMap.put("createUser", user.getUserId());
        paramMap.put("groupCode", user.getGroupCode());
        paramMap.put("updateUser", user.getUserId());
        paramMap.put("instanceStatus", FlowConstant.BUSINESS_INSTANCE_STATUS_APPROVAL);
        String pkey = StringUtil.getUUID();
        paramMap.put("pkey", pkey);
        super.setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
        addLog(namespace, "添加", paramMap, request, LogDesc.ADD);
        PagexListSettVO listPageSett = PagexDataService.SIGNEL.getPagexListSettDTOFromCache(namespace);
        StartProcessInstanceVO startProcessInstanceVO = new StartProcessInstanceVO();
        startProcessInstanceVO.setBusinessKey(pkey);
        // 流程key要在js中配置
        startProcessInstanceVO.setProcessDefinitionKey(ConverterUtils.toString(listPageSett.getModelConfig().get("processDefinitionKey")));
        startProcessInstanceVO.setVariables(paramMap);
        startProcessInstanceVO.setExtFormParam(new HashMap<>());
        startProcessInstanceVO.setUserId(getSessionUser(request).getUserId());
        HttpResult<String> rpcResult = feignWorkFlowApiService.startProcessInstanceForApi(startProcessInstanceVO);
        if(rpcResult.getCode() != Constant.SUCCESS_CODE){
            throw new ParamException("调用流程启动错误");
        }
        paramMap.put("instanceId",rpcResult.getData());
        service.insert(paramMap, namespace);
        refreshPageXTransCache(namespace);
        return HttpResult.success(true);

    }



    /**
     * 更新
     *
     * @param namespace namespace
     * @param id        id
     */
    @RequestMapping("{namespace}/reSubmit/{taskId}/{id}")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult<Boolean> reSubmit(@PathVariable("namespace") String namespace,  @PathVariable("taskId") String taskId,@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        checkPermiessAndNamespace(namespace, "update");
        EMap<String, Object> paramMap = super.getParameterMap();
        paramMap.put("id", id);
        paramMap.put("groupCode", MultiTenancyContext.getProviderId());
        paramMap.put("updateUser", getSessionUser(request).getUserId());
        addLog(namespace, "更新", paramMap, request, LogDesc.UPDATE);
        super.setDB(PagexDataService.SIGNEL.getPagexAddDTOFromCache(namespace));
        paramMap.put("instanceStatus", FlowConstant.BUSINESS_INSTANCE_STATUS_APPROVAL);
        int i = service.update(paramMap, namespace);
        ReSubmitVO reSubmitVO = new ReSubmitVO();
        paramMap.put("result",FlowConstant.RESULT_SUBMIT);
        reSubmitVO.setVariablesMap(paramMap);
        reSubmitVO.setTaskId(taskId);
        feignWorkFlowApiService.reSubmit(reSubmitVO);
        refreshPageXTransCache(namespace);
        return HttpResult.success(i != 0);
    }

}
