package com.fhs.workflow.action;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.core.exception.ParamChecker;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.fhs.workflow.service.WorkFlowJbpmXmlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程xml管理
 * @author  wanglei
 * @version  [版本号, 2017/07/25 11:04:23]
 */
@Controller
@RequestMapping("workFlowJbpmXml")
public class WorkFlowJbpmXmlAction extends ModelSuperAction<WorkFlowJbpmXml>
{
	@Autowired
	private WorkFlowJbpmXmlService workFlowJbpmXmlService;

	
	/**
     * 部署工作流到引擎里面
     * @param request request
     * @param response response
     * @param xmlId 里面包含id即可
     */
    @RequestMapping("releaseWorkFlow")
    @RequiresPermissions("workFlowJbpmXml:releaseWorkFlow")
    public void releaseWorkFlow(HttpServletRequest request, HttpServletResponse response,String  xmlId)
    {
        ParamChecker.isNotNull(xmlId,"id不能为空");
        workFlowJbpmXmlService.releaseWorkFlow(xmlId);
        super.outToClient(true, response);
    } 
    
    

    

    
}