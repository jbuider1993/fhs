package com.fhs.flow.vo;
import lombok.Data;
import java.util.Map;

/**
 * 表单编辑后重新提交表单的vo
 */
@Data
public class ReSubmitVO {
    /**
     * 流程变量
     */
    private Map<String,Object> variablesMap;

    /**
     * 任务id
     */
    private String taskId;
}
