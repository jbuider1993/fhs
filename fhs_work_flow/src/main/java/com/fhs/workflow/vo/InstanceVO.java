package com.fhs.workflow.vo;

import com.fhs.workflow.bean.FlowInstance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实例VO
 * @author Jackwong
 * @date 2019 -11-15 14:03:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanceVO extends FlowInstance {

    /**
     * 流程id
     */
    private String processKey;

    /**
     * 是否是提交信息的节点
     */
    private  Integer isSubmiTask;

    /**
     * 表单的url
     */
    private String formUrl;

    /**
     * 是否可以撤回
     */
    private Integer isCanWithdraw;


}
