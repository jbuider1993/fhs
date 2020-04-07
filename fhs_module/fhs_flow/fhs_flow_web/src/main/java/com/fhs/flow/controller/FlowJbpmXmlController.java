package com.fhs.flow.controller;


import com.fhs.common.utils.StringUtil;
import com.fhs.core.result.HttpResult;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.core.valid.group.Add;
import com.fhs.flow.constant.FlowConstant;
import com.fhs.flow.dox.FlowJbpmXmlDO;
import com.fhs.flow.service.FlowJbpmXmlService;
import com.fhs.flow.vo.FlowJbpmXmlVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程列表-xml(FlowJbpmXml)表控制层
 *
 * @author jackwong
 * @since 2019-11-11 14:29:04
 */
@RestController
@RequestMapping("/ms/flow_jbpm_xml")
public class FlowJbpmXmlController extends ModelSuperController<FlowJbpmXmlVO, FlowJbpmXmlDO> {

    @Autowired
    private FlowJbpmXmlService flowJbpmXmlService;

    /**
     * 部署工作流到引擎里面
     *
     * @param xmlId 里面包含id即可
     */
    @RequestMapping("releaseWorkFlow")
    @RequiresPermissions("flow_jbpm_xml:releaseWorkFlow")
    public HttpResult<Boolean> releaseWorkFlow(String xmlId) {
        ParamChecker.isNotNull(xmlId, "id不能为空");
        flowJbpmXmlService.releaseWorkFlow(xmlId);
        return HttpResult.success(true);
    }

    /**
     * 添加流程数据
     *
     * @param flowJbpmXmlVO 里面包含id即可
     */
    @RequestMapping("addFlow")
    @RequiresPermissions("flow_jbpm_xml:add")
    public HttpResult<Boolean> addFlow(@Validated(Add.class) FlowJbpmXmlVO flowJbpmXmlVO) {
        flowJbpmXmlVO.setStatus(FlowConstant.XML_STATUS_DRAFT);
        flowJbpmXmlVO.setVersion(1);
        flowJbpmXmlVO.preInsert(super.getSessionuser().getUserId());
        flowJbpmXmlService.insertSelective(flowJbpmXmlVO);
        return HttpResult.success(true);
    }


}