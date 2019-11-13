package com.fhs.workflow.action;

import com.fhs.workflow.bean.FlowInstance;
import com.fhs.workflow.service.FlowInstanceService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.fhs.base.action.ModelSuperAction;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表控制层
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@RestController
@RequestMapping("/ms/flow_instance")
public class FlowInstanceAction extends ModelSuperAction<FlowInstance> {
   

}