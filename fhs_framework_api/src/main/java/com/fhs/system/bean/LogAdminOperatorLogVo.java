package com.fhs.system.bean;

import com.fhs.core.base.vo.BaseVo;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @author  qixiaobo
 * @version [版本号, 2018-08-11]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogAdminOperatorLogVo extends BaseVo<LogAdminOperatorLogVo> {

    private static final long serialVersionUID = 1L;
    
    /**
     * id,主键自增
     */
    @NotNull(message="log的id字段 不可为null ", groups = {Update.class, Delete.class})
    @Max(message="log的id超过int最大值", value=2147483647, groups = {Delete.class, Update.class})
    @Min(message="log的id小于int最大值", value=-2147483648, groups = {Delete.class, Update.class})
    private Integer id;
    /**
     * 操作用户id
     */
    @NotNull(message="操作用户id字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "操作用户id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    private String operatorId;

    /**
     * 创建时间
     */
    @NotNull(message="创建时间字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "创建时间字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    private String createTime;

    /**
     * 操作类型(0增，1删，2改，3查，4其他)
     */
    @Max(message = "操作类型(0增，1删，2改，3查，4其他)字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "操作类型(0增，1删，2改，3查，4其他)字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    private Integer logType;
    /**
     * 请求的url
     */
	@NotEmpty
	@NotNull(message="请求的url字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "请求的url字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    private String url;
    /**
     * 操作描述
     */
	@NotEmpty
	@NotNull(message="操作描述字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "操作描述字段的长度最大为500", groups = {Add.class, Update.class}, max = 500)
    private String operatDesc;
    /**
     * 参数
     */
	@Length(message = "参数字段的长度最大为", groups = {Add.class, Update.class})
    private String reqParam;
    /**
     * 菜单id
     */
    @Max(message = "菜单id字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "菜单id字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    private Integer menuId;
    /**
     * ip地址
     */
	@NotEmpty
	@NotNull(message="ip地址字段不可为null", groups = {Update.class, Delete.class})
	@Length(message = "ip地址字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    private String networkIp;

    /**
     * 集团编码
     */
    private String groupCode;
}
