package com.fhs.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.base.bean.BaseDO;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 自建工作流实例
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/31 12:26:33]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@Builder
@TableName("t_work_flow_instance")
public class WorkFlowInstance extends BaseDO<WorkFlowInstance>
{
 	/** 
	 * 
	 */
	@NotNull(message="{workflow.id.null}", groups = {Update.class, Delete.class})
	@Max(message="{workflow.id.max}", value=2147483647, groups = {Delete.class, Update.class})
	@Min(message="{workflow.id.min}", value=-2147483648, groups = {Delete.class, Update.class})
	@Id
	@Column(name = "id", nullable = false, length = 10)
	private Integer id;
 	/** 
	 * 标题
	 */
	@NotNull(message="{workflow.title.null}")
	@Length(message="{workflow.title.length}", groups = {Add.class, Update.class}, max=32, min=0)
	@Column(name = "title", nullable = false, length = 32)
	private String title;
 	/** 
	 * 创建人
	 */
	@Length(message="{workflow.creator.length}", groups = {Add.class, Update.class}, max=32, min=0)
	@Column(name = "creator", nullable = true, length = 32)
	private String creator;
 	/** 
	 * 创建人id
	 */
	@Max(message="{workflow.creatorId.max}", value=2147483647, groups = {Add.class, Update.class})
	@Min(message="{workflow.creatorId.min}", value=-2147483648, groups = {Add.class, Update.class})
	@Column(name = "creator_id", nullable = true, length = 10)
	private Integer creatorId;
 	/** 
	 * 创建时间
	 */
	@NotNull(message="{workflow.createDatetime.null}")
	@Column(name = "create_datetime", nullable = false)
	private String createDatetime;
 	/** 
	 * 工作流类型
	 */
	@NotNull(message="{workflow.workFlowTypeId.null}")
	@Max(message="{workflow.workFlowTypeId.max}", value=2147483647, groups = {Add.class, Update.class})
	@Min(message="{workflow.workFlowTypeId.min}", value=-2147483648, groups = {Add.class, Update.class})
	@Column(name = "work_flow_type_id", nullable = false, length = 10)
	private Integer workFlowTypeId;
 	/** 
	 * 工作流id
	 */
	@NotNull(message="{workflow.workFlowId.null}")
	@Max(message="{workflow.workFlowId.max}", value=2147483647, groups = {Add.class, Update.class})
	@Min(message="{workflow.workFlowId.min}", value=-2147483648, groups = {Add.class, Update.class})
	@Column(name = "work_flow_id", nullable = false, length = 10)
	private Integer workFlowId;
 	/** 
	 * 引擎中工作流实例id
	 */
	@NotNull(message="{workflow.processInstanceId.null}")
	@Length(message="{workflow.processInstanceId.length}", groups = {Add.class, Update.class}, max=32, min=0)
	@Column(name = "process_instance_id", nullable = false, length = 32)
	private String processInstanceId;
 	/** 
	 * formid
	 */
	@NotNull(message="{workflow.formId.null}")
	@Max(message="{workflow.formId.max}", value=2147483647, groups = {Add.class, Update.class})
	@Min(message="{workflow.formId.min}", value=-2147483648, groups = {Add.class, Update.class})
	@Column(name = "form_id", nullable = false, length = 10)
	private Integer formId;
 	/** 
	 * 表单名称
	 */
	@Length(message="{workflow.formName.length}", groups = {Add.class, Update.class}, max=32, min=0)
	@Column(name = "form_key", nullable = true, length = 32)
	private String formKey;
 	/** 
	 * 扩展数据json
	 */
	@Column(name = "extend_json", nullable = true, length = 65535)
	private String extendJson;
	
	/**
	 * 最新的task名称 
	 */
	private String newTaskName;


	public WorkFlowInstance(){}

	public WorkFlowInstance(Integer id, String title, String creator, Integer creatorId, String createDatetime, Integer workFlowTypeId, Integer workFlowId, String processInstanceId, Integer formId, String formKey, String extendJson, String newTaskName) {
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.creatorId = creatorId;
		this.createDatetime = createDatetime;
		this.workFlowTypeId = workFlowTypeId;
		this.workFlowId = workFlowId;
		this.processInstanceId = processInstanceId;
		this.formId = formId;
		this.formKey = formKey;
		this.extendJson = extendJson;
		this.newTaskName = newTaskName;
	}
}
