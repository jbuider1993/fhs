package com.fhs.workflow.action;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.result.HttpResult;
import com.fhs.workflow.bean.FlowInstance;
import com.fhs.workflow.service.FlowInstanceService;
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
public class FlowInstanceAction extends ModelSuperAction<FlowInstance> {

    @Autowired
    private FlowInstanceService flowInstanceService;

    /**
     * 是否执行完毕
     */
    @RequestMapping("isRevokeApply")
    public HttpResult<Boolean> isRevokeApply(String processInstanceId, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(processInstanceId, "流程实例id不能为空");
        FlowInstance flowInstance = new FlowInstance();
        flowInstance.setActivitiProcessInstanceId(processInstanceId);
        flowInstance.setCreateUser(this.getSessionuser(request).getUserId());
        FlowInstance instance = flowInstanceService.selectBean(flowInstance);
        if (instance == null) {
            return HttpResult.success(false);
        }
        return HttpResult.success(true);
    }


}