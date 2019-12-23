package com.fhs.workflow.action;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.DateUtils;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.page.Pager;
import com.fhs.core.trans.TransService;
import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.service.FlowTaskHistoryService;
import com.fhs.workflow.vo.TaskHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 流程任务日志(FlowTaskHistory)表控制层
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
@RestController
@RequestMapping("/ms/flowTaskHistory")
public class FlowTaskHistoryAction extends ModelSuperAction<FlowTaskHistory> {

    @Autowired
    private FlowTaskHistoryService flowTaskHistoryService;

    @Autowired
    private TransService transService;

    /**
     * 办理历史
     *
     * @param request
     * @return
     */
    @RequestMapping("getTaskHistoryList")
    public Pager<TaskHistoryVO> getTaskHistoryList(HttpServletRequest request) {
        Map<String, Object> paramMap = super.getPageTurnNum(request);
        paramMap.put("userId", this.getSessionuser(request).getUserId());
        List<TaskHistoryVO> alreadyDoneList = flowTaskHistoryService.findTaskHistoryList(paramMap);
        alreadyDoneList.forEach(alreadyDoneVO -> {
            alreadyDoneVO.setUseTime(DateUtils.timeCount(ConverterUtils.toInt(alreadyDoneVO.getUseTime())));
        });
        transService.transMore(alreadyDoneList);
        int alreadyDoneCount = flowTaskHistoryService.findTaskHistoryCount(paramMap);
        return new Pager(alreadyDoneCount, alreadyDoneList);
    }

    /**
     * 审批历史
     */
    @RequestMapping("getApprovalRecord")
    public Pager<TaskHistoryVO> getApprovalRecord(String instanceId) {
        ParamChecker.isNotNullOrEmpty(instanceId, "流程实例id不能为空");
        List<TaskHistoryVO> approvalRecord = flowTaskHistoryService.findApprovalRecord(instanceId);
        transService.transMore(approvalRecord);
        return new Pager(approvalRecord.size(), approvalRecord);
    }


}