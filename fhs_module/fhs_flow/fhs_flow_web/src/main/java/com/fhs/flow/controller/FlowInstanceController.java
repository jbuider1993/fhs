package com.fhs.flow.controller;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.pojo.pager.Pager;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public HttpResult<Boolean> isRevokeApply(String instanceId, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(instanceId, "流程实例id不能为空");
        FlowInstanceVO flowInstance = new FlowInstanceVO();
        flowInstance.setActivitiProcessInstanceId(instanceId);
        flowInstance.setCreateUser(this.getSessionuser().getUserId());
        FlowInstanceVO instance = flowInstanceService.selectBean(flowInstance);
        if (instance == null) {
            return HttpResult.success(false);
        }
        return HttpResult.success(true);
    }

    @Override
    public Pager<FlowInstanceVO> findPage(FlowInstanceVO e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        e.setCcTo(super.getSessionuser().getUserId());
        return super.findPage(e, request, response);
    }

    /**
     *  更新抄送人
     * @param ccTo 抄送人
     * @return
     */
    @RequestMapping("updateCCTo")
    public HttpResult updateCCTo(String ccTo, String instanceId) {
        ParamChecker.isNotNull(ccTo, "抄送人不能为空");
        ParamChecker.isNotNull(instanceId, "instanceId不能为空");
        FlowInstanceVO instanceVO = this.flowInstanceService.selectBean(FlowInstanceDO.builder().activitiProcessInstanceId(instanceId).build());
        ParamChecker.isNotNull(instanceVO, "instanceId无效");
        if(CheckUtils.isNullOrEmpty(instanceVO.getCcTo())){
            instanceVO.setCcTo(ccTo);
        }else{
            Set<String> ccToSet = new HashSet<>(Arrays.asList(instanceVO.getCcTo().split(",")));
            ccToSet.addAll(Arrays.asList(ccTo.split(",")));
            instanceVO.setCcTo(StringUtil.getStrForIn(ccToSet,false));
        }
        flowInstanceService.updateJpa(instanceVO);
        return HttpResult.success();
    }


}