package com.fhs.workflow.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fhs.core.group.*;
import com.fhs.core.base.bean.BaseDO;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)实体类
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */

@Data
@Builder
@TableName("t_flow_instance")
public class FlowInstance extends BaseDO<FlowInstance> {
    private static final long serialVersionUID = -80075792071824152L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 实例标题
     */
    @NotEmpty
    @NotNull(message = "实例标题字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "实例标题字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("title")
    private String title;

    /**
     * xml id
     */
    @NotEmpty
    @NotNull(message = "xml id字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "xml id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("xml_id")
    private String xmlId;

    /**
     * activiti中流程实例id
     */
    @Length(message = "activiti中流程实例id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("activiti_process_instance_id")
    private String activitiProcessInstanceId;

    /**
     * 流程外键
     */
    @NotEmpty
    @NotNull(message = "流程外键字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "流程外键字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("form_pkey")
    private String formPkey;

    /**
     * 扩展参数-在打开表单的时候会附加上
     */
    @NotEmpty
    @NotNull(message = "扩展参数-在打开表单的时候会附加上字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "扩展参数-在打开表单的时候会附加上字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("ext_form_param")
    private String extFormParam;

    /**
     * 状态
     */
    @TableField("status")
    private int status;


    public FlowInstance() {
    }


    public FlowInstance(String id, String title, String xmlId, String activitiProcessInstanceId, String formPkey, String extFormParam, int status) {
        this.id = id;
        this.title = title;
        this.xmlId = xmlId;
        this.activitiProcessInstanceId = activitiProcessInstanceId;
        this.formPkey = formPkey;
        this.extFormParam = extFormParam;
        this.status = status;
    }
}