/*
package com.fhs.workflow.service.impl;

import java.util.List;
import java.util.Map;

import com.fhs.common.utils.EMap;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.fhs.workflow.service.PubWorkFlowService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jack.base.dao.db.DbParam;
import com.jack.base.dao.db.WhereValue;
import com.jack.base.dao.enums.DbOperEnum;
import com.jack.base.service.DBPubService;
import com.jack.common.utils.CalendarUtil;
import com.jack.common.utils.ConverterUtils;
import com.jack.common.utils.EMap;
import com.jack.common.utils.JsonUtils;
import com.jack.common.utils.MapUtils;
import com.jack.form.bean.FormDesign;
import com.jack.form.bean.FormField;
import com.jack.form.service.FormDesignService;
import com.jack.workflow.bean.WorkFlowInstance;
import com.jack.workflow.bean.WorkFlowJbpmXml;
import com.jack.workflow.service.PubWorkFlowService;


@Service("PubWorkFlowServiceImpl")
public class PubWorkFlowServiceImpl implements PubWorkFlowService {

    */
/**
     * 引擎工厂
     *//*

    @Autowired
    private ProcessEngineFactoryBean processEngineFactoryBean;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DBPubService<WorkFlowJbpmXml> jbpmXmlDBpubService;
    
    @Autowired
    private DBPubService<WorkFlowInstance> instanceDBpubService;
    
    @Autowired
    private DBPubService<FormDesign> formDBpubService;
    
    @Autowired
    private DBPubService<FormField> formFieldDBpubService;
    
    @Autowired
    private FormDesignService formDesignService;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String startFlowForPubForm(EMap<String, Object> paramMap) throws Exception {
        WorkFlowJbpmXml WorkFlowJbpmXml = jbpmXmlDBpubService.get(WorkFlowJbpmXml.class, paramMap.getStr("workFlowId"));
        */
/**
         * 启动一个工作流，将表单保存到数据库中
         * 1 给自建工作流实例表添加一条数据
         * 2 给流程引擎添加数据(注意流程变量处理)
         * 3 保存表单数据
         * 4 更新自建流程实例数据
         * @param paramMap 包含表单数据 流程id 用户信息
         * @return businesskey
         *//*

        FormDesign form = formDBpubService.get(FormDesign.class,paramMap.getStr("formId"));
        DbParam dbParam = new DbParam();
        dbParam.getWheres().add(new WhereValue("tableName", DbOperEnum.OPERA_EQ, form.getName()));
        List<FormField> fieldList = formFieldDBpubService.getListByEntity(FormField.class, dbParam);
        //保存表单
        formDesignService.saveFormInfo(paramMap, form, fieldList);
        
        //自建工作流实例表
        WorkFlowInstance workFlowInstance = new WorkFlowInstance();
        workFlowInstance.setFormId(form.getId());
        workFlowInstance.setTitle(WorkFlowJbpmXml.getProcessName());
        workFlowInstance.setCreator(paramMap.getStr("userName"));
        workFlowInstance.setCreatorId(paramMap.getInteger("userId"));
        workFlowInstance.setCreateDatetime(CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"));
        workFlowInstance.setWorkFlowId(WorkFlowJbpmXml.getId());
        workFlowInstance.setWorkFlowTypeId(WorkFlowJbpmXml.getTypeId());
        workFlowInstance.setFormKey(paramMap.getStr("id"));
        String workFlowInstanceId = instanceDBpubService.save(workFlowInstance);
        workFlowInstance.setId(ConverterUtils.toInt(workFlowInstanceId));
        
        //给引擎添加一个实例
        RuntimeService runtimeService = processEngineFactoryBean.getObject().getRuntimeService();  
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(WorkFlowJbpmXml.getProcessKey(),workFlowInstanceId,formDesignService.getVariables(paramMap, fieldList));
        workFlowInstance.setProcessInstanceId(processInstance.getProcessInstanceId());
        
        //更新自建实例
        instanceDBpubService.update(workFlowInstance);
        
        return workFlowInstanceId;
    }
    
    

    @SuppressWarnings("unchecked")
    @Override
    public String complateTask(EMap<String, Object> paramMap) throws Exception {
        Task task = taskService.createTaskQuery().taskId(paramMap.getStr("taskId")).singleResult();
        DbParam dbParam = new DbParam();
        dbParam.getWheres().add(new WhereValue("name", DbOperEnum.OPERA_EQ, task.getFormKey()));
        FormDesign form = formDBpubService.getEntity(FormDesign.class,dbParam);
        dbParam = new DbParam();
        dbParam.getWheres().add(new WhereValue("tableName", DbOperEnum.OPERA_EQ, form.getName()));
        List<FormField> fieldList = formFieldDBpubService.getListByEntity(FormField.class, dbParam);
        //保存表单
        formDesignService.saveFormInfo(paramMap, form, fieldList);
        
        taskService.complete(paramMap.getStr("taskId"),formDesignService.getVariables(paramMap, fieldList));
        
        //自建工作流实例表
        WorkFlowInstance workFlowInstance = new WorkFlowInstance();
        workFlowInstance.setId(paramMap.getInteger("businessKey"));
        workFlowInstance = instanceDBpubService.get(workFlowInstance);
        List<Map<Object, Object>> listMap = JSON.parseObject(workFlowInstance.getExtendJson(), new TypeReference<List<Map<Object,Object>>>(){});
        listMap.add((Map<Object, Object>) MapUtils.getMapFromArgs(new Object[]{"formKey",paramMap.get("id"),"formId",form.getId(),"mainArgs",formDesignService.getVariableStr(paramMap, fieldList),
                "taskName",task.getName(),"complateDateTime",CalendarUtil.getCurrentTimeStr("yyyy-MM-dd HH:mm:ss"),"operator",paramMap.get("userName"),
                "startDateTime",task.getCreateTime()}));
        workFlowInstance.setExtendJson(JsonUtils.list2json(listMap));
        instanceDBpubService.update(workFlowInstance);
        return paramMap.getStr("id");
    }


     
   
    

}
*/
