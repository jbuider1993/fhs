package com.fhs.flow.controller;

import com.fhs.core.result.HttpResult;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.flow.dox.FlowInstanceDO;
import com.fhs.flow.service.FlowInstanceService;
import com.fhs.flow.vo.FlowInstanceVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表控制层
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@RestController
@RequestMapping("/ms/flow_instance")
public class FlowInstanceController extends ModelSuperController<FlowInstanceVO, FlowInstanceDO> {

    @Autowired
    private FlowInstanceService flowInstanceService;

    /**
     * 是否执行完毕
     */
    @RequestMapping("isRevokeApply")
    public HttpResult<Boolean> isRevokeApply(String processInstanceId, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(processInstanceId, "流程实例id不能为空");
        FlowInstanceVO flowInstance = new FlowInstanceVO();
        flowInstance.setActivitiProcessInstanceId(processInstanceId);
        flowInstance.setCreateUser(this.getSessionuser().getUserId());
        FlowInstanceVO instance = flowInstanceService.selectBean(flowInstance);
        if (instance == null) {
            return HttpResult.success(false);
        }
        return HttpResult.success(true);
    }


}