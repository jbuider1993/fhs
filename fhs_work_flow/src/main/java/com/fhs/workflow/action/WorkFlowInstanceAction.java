package com.fhs.workflow.action;

import javax.annotation.Resource;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.workflow.bean.WorkFlowInstance;
import com.fhs.workflow.service.WorkFlowInstanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自建工作流实例
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/31 12:26:34]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("workFlowInstance")
public class WorkFlowInstanceAction extends ModelSuperAction<WorkFlowInstance>
{

}