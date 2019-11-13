package com.fhs.workflow.action;

import com.fhs.workflow.bean.FlowListener;
import com.fhs.workflow.service.FlowListenerService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.fhs.base.action.ModelSuperAction;

/**
 * 监听器(FlowListener)表控制层
 *
 * @author jackwong
 * @since 2019-11-11 14:28:44
 */
@RestController
@RequestMapping("/ms/flowListener")
public class FlowListenerAction extends ModelSuperAction<FlowListener> {
   

}