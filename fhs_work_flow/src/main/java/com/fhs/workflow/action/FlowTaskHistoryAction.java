package com.fhs.workflow.action;

import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.service.FlowTaskHistoryService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.fhs.base.action.ModelSuperAction;

/**
 * 流程任务日志(FlowTaskHistory)表控制层
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
@RestController
@RequestMapping("/ms/flowTaskHistory")
public class FlowTaskHistoryAction extends ModelSuperAction<FlowTaskHistory> {
   

}