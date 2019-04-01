package com.fhs.pubservice.bean;

import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @Description:生成订单号
 * @author  jianbo.qin
 * @version  [版本号, 2018/05/10 15:09:42]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 * */
@Entity
@Table(name="t_service_order_log")
public class ServiceOrderLog extends SuperBean<ServiceOrderLog> 
{
	private static final long serialVersionUID = 1L;
	/** 
	 * 
	 */
	@NotNull(message="pubservice的id字段 不可为null ", groups = {Update.class, Delete.class})
	@Id
	@Column(name = "id", nullable = false, length = 32)
	private String id;
 
	/** 
	 * 
	 */
	@Length(message="pubservice的type字段的长度最大为20", groups = {Add.class, Update.class}, max=20, min=0)
	@Column(name = "type", nullable = true, length = 20)
	private String type;
 
	/** 
	 * 
	 */
	@Length(message="pubservice的time字段的长度最大为50", groups = {Add.class, Update.class}, max=50, min=0)
	@Column(name = "time", nullable = true, length = 50)
	private String time;
 
	/** 
	 * 
	 */
	@Max(message="{pubservice的number字段大于int最大值}", value=2147483647, groups = {Add.class, Update.class})
	@Min(message="{pubservice的number字段小于int小值", value=-2147483648, groups = {Add.class, Update.class})
	@Column(name = "number", nullable = true, length = 10)
	private Integer number;

	/** 
	 * 给赋值
	 */
	public void setId(String id)
	{
		this.id=id;
	}
 
	/** 
	 * 获取
	 */
	public String getId()
	{
		return id;
	}
 
	/** 
	 * 给赋值
	 */
	public void setType(String type)
	{
		this.type=type;
	}
 
	/** 
	 * 获取
	 */
	public String getType()
	{
		return type;
	}
 
	/** 
	 * 给赋值
	 */
	public void setTime(String time)
	{
		this.time=time;
	}
 
	/** 
	 * 获取
	 */
	public String getTime()
	{
		return time;
	}
 
	/** 
	 * 给赋值
	 */
	public void setNumber(Integer number)
	{
		this.number=number;
	}
 
	/** 
	 * 获取
	 */
	public Integer getNumber()
	{
		return number;
	}

}
