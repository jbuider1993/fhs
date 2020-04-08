package com.fhs.flow.vo;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.pojo.vo.VO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.trans.constant.TransType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 已办纪录
 *
 * @author yutao
 * @date 2019 -11-15 09:47:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TransTypes(types = {TransType.AUTO_TRANS,TransType.WORD_BOOK})
public class TaskHistoryVO implements VO {

    private String taskId;

    /**
     * 流程标题
     */
    private String processTitle;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 流程状态
     */
    @Trans(type = TransType.WORD_BOOK, key = "workFlow_process_status")
    private String processStatus;

    /**
     * 发起人
     */
    @Trans(type = TransType.AUTO_TRANS, key = Constant.USER_INFO)
    private String createUser;

    /**
     * 办理时间
     */
    private String taskFinishTime;

    /**
     * 处理用时
     */
    private String useTime;

    /**
     * 处理结果
     */
    @Trans(type = TransType.WORD_BOOK, key = "workFlow_use_status")
    private String useStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String remark;


    /**
     * 翻译map
     */
    private Map<String, String> transMap = new HashMap<>();


    @Override
    public Object getPkey() {
        return this.taskId;
    }

    @Override
    public Integer getIsDelete() {
        return Constant.INT_FALSE;
    }
}
