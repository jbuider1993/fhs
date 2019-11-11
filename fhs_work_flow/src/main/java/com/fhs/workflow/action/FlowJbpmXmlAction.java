package com.fhs.workflow.action;

import com.fhs.core.exception.ParamChecker;
import com.fhs.core.result.HttpResult;
import com.fhs.workflow.bean.FlowJbpmXml;
import com.fhs.workflow.service.FlowJbpmXmlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhs.base.action.ModelSuperAction;

/**
 * 流程列表-xml(FlowJbpmXml)表控制层
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:29:04
 */
@RestController
@RequestMapping("/ms/flowJbpmXml")
public class FlowJbpmXmlAction extends ModelSuperAction<FlowJbpmXml> {

    @Autowired
    private FlowJbpmXmlService flowJbpmXmlService;

    /**
     * 部署工作流到引擎里面
     * @param xmlId 里面包含id即可
     */
    @RequestMapping("releaseWorkFlow")
    @RequiresPermissions("workFlowJbpmXml:releaseWorkFlow")
    public HttpResult<Boolean> releaseWorkFlow(String  xmlId)
    {
        ParamChecker.isNotNull(xmlId,"id不能为空");
        flowJbpmXmlService.releaseWorkFlow(xmlId);
        return HttpResult.success(true);
    }


}