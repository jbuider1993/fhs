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

import com.jack.base.controller.SuperAction;
import com.jack.base.dao.db.DbParam;
import com.jack.base.dao.db.WhereValue;
import com.jack.base.dao.enums.DbOperEnum;
import com.jack.base.service.DBPubService;
import com.jack.common.constant.Constant;
import com.jack.common.utils.JsonUtils;
import com.jack.form.bean.FormDesign;
import com.jack.workflow.bean.WorkFlowJbpmXml;
import com.jack.workflow.service.WorkFlowJbpmXmlService;

/**
 * 流程xml管理
 * @author  wanglei
 * @version  [版本号, 2017/07/25 11:04:23]
 */
@Controller
@RequestMapping("workFlowJbpmXml")
public class WorkFlowJbpmXmlAction extends ModelSuperAction<WorkFlowJbpmXml>
{
	@Resource(name = "workFlowJbpmXmlServiceImpl")
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
    
    
    /**
     * 获取已经发布的数据tree
     * @param request request
     * @param response response
     */
    @RequestMapping("getWorkFlowTree")
    @RequiresPermissions("workFlowJbpmXml:see")
    public void getWorkFlowTree(HttpServletRequest request, HttpServletResponse response)
    { 
        DbParam param = super.getPageParamsMap(request);
        param.getWheres().add(new WhereValue("isPubForm", DbOperEnum.OPERA_EQ, Constant.INT_TRUE + ""));
        String resultJson = workFlowJbpmXmlService.getWorkFlowTreeJson(param);
        super.outWriteJson(resultJson, response);
    } 
    
    /**
     * 获取第一个页面数据
     * @param request request
     * @param response response
     * @throws Exception 
     */
    @RequestMapping("getFirstForm")
    public void getFirstForm(HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        WorkFlowJbpmXml workFlowJbpmXml = super.getDBpubService().get(WorkFlowJbpmXml.class, request.getParameter("id"));
        String formName = workFlowJbpmXml.getFormName();
        DbParam dbParam = new DbParam();
        dbParam.getWheres().add(new WhereValue("name", DbOperEnum.OPERA_EQ, formName));
        FormDesign formDesign = formDBPubService.getEntity(FormDesign.class, dbParam);
        super.outWriteJson(JsonUtils.bean2json(formDesign), response);
    } 
    
    
}