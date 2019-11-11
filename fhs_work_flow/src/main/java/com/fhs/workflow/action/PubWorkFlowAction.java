package com.fhs.workflow.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jack.base.controller.BaseAction;
import com.jack.base.dao.db.DbParam;
import com.jack.base.dao.db.WhereValue;
import com.jack.base.dao.enums.DbOperEnum;
import com.jack.base.service.DBPubService;
import com.jack.common.utils.CheckUtils;
import com.jack.common.utils.JsonUtils;
import com.jack.common.utils.MapUtils;
import com.jack.form.bean.FormDesign;
import com.jack.form.service.FormDesignService;
import com.jack.workflow.service.PubWorkFlowService;

/**
 * 公共的表单与工作流对接
 * @author  wanglei
 * @version  [版本号, 2017/07/31 12:26:34]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//@Controller
//@RequestMapping("pubWorkFlow")
public class PubWorkFlowAction extends BaseAction
{
    @Autowired
    private PubWorkFlowService pubWorkFlowService;
    
    @Autowired
    private DBPubService<FormDesign> dbpubService;
    
    @Autowired
    private FormDesignService ormDesignService;
    
    /**
     * 部署工作流到引擎里面
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("start")
    public void start(HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        String businessKey = pubWorkFlowService.startFlowForPubForm(super.getParameterMap(request));
        super.outWriteJson(JsonUtils.map2json(MapUtils.getMapFromArgs(new Object[]{"businessKey",businessKey})), response);
    } 
    

    /**
     * 完成工作
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("complateTask")
    public void complateTask(HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        String formId = pubWorkFlowService.complateTask(super.getParameterMap(request));
        super.outWriteJson(JsonUtils.map2json(MapUtils.getMapFromArgs(new Object[]{"formId",formId})), response);
    } 
    
    /**
     * 获取form的数据
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getFormData")
    public void getFormData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,Object> formData = ormDesignService.getFormData(request.getParameter("formName"), request.getParameter("id"));
        super.outWriteJson(JsonUtils.map2json(formData), response);
    }
    
    /**
     * 获取form设计数据
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getFormDesignInfo")
    public void getFormDesignInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        FormDesign form = null;
        if(!CheckUtils.isNullOrEmpty(request.getParameter("formId")))
        {
            form = dbpubService.get(FormDesign.class, request.getParameter("formId"));
        }
        else
        {
            DbParam dbParam = new DbParam();
            dbParam.getWheres().add(new WhereValue("name", DbOperEnum.OPERA_EQ, request.getParameter("formName")));
            form = dbpubService.getEntity(FormDesign.class, dbParam);
        }
        super.outWriteJson(JsonUtils.bean2json(form), response);
    }
    
}
