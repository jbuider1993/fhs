package com.fhs.system.bean;

import com.mybatis.jpa.annotation.Like;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.group.Add;
import com.fhs.core.group.Delete;
import com.fhs.core.group.Update;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "t_log_admin_operator_log")
@TransTypes(types = {Constant.WORD_BOOK, Constant.USER_INFO})
public class LogAdminOperatorLog extends SuperBean<LogAdminOperatorLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id,主键自增
     */
    @NotNull(message="log的id字段 不可为null ", groups = {Update.class, Delete.class})
    @Max(message="log的id超过int最大值", value=2147483647, groups = {Delete.class, Update.class})
    @Min(message="log的id小于int最大值", value=-2147483648, groups = {Delete.class, Update.class})
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;
    /**
     * 操作用户id
     */
    @NotNull(message="操作用户id字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "操作用户id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "operator_id")
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    private String operatorId;

    /**
     * 创建时间
     */
    @NotNull(message="创建时间字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "创建时间字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "create_time")
    private String createTime;

    /**
     * 操作类型(0增，1删，2改，3查，4其他)
     */
    @Max(message = "操作类型(0增，1删，2改，3查，4其他)字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "操作类型(0增，1删，2改，3查，4其他)字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "log_type")
    @Trans(type = Constant.WORD_BOOK, key = "log_type")
    private Integer logType;
    /**
     * 请求的url
     */
    @NotEmpty
    @NotNull(message="请求的url字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "请求的url字段的长度最大为200", groups = {Add.class, Update.class}, max = 200)
    @Column(name = "url")
    private String url;
    /**
     * 操作描述
     */
    @NotEmpty
    @NotNull(message="操作描述字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "操作描述字段的长度最大为500", groups = {Add.class, Update.class}, max = 500)
    @Column(name = "operat_desc")
    @Like
    private String operatDesc;
    /**
     * 参数
     */
    @Length(message = "参数字段的长度最大为", groups = {Add.class, Update.class})
    @Column(name = "req_param")
    @Like
    private String reqParam;
    /**
     * 菜单id
     */
    @Max(message = "菜单id字段大于int最大值", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "菜单id字段小于int最小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "menu_id")
    private Integer menuId;
    /**
     * ip地址
     */
    @NotEmpty
    @NotNull(message="ip地址字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "ip地址字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @Column(name = "network_ip")
    @Like
    private String networkIp;

    /**
     * 参数
     */
    @Column(name = "group_code")
    private String groupCode;

    public LogAdminOperatorLog(){
    }

    public LogAdminOperatorLog(String id, String operatorId, String createTime, Integer logType, String url, String operatDesc, String reqParam, Integer menuId, String networkIp, String groupCode) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.logType = logType;
        this.url = url;
        this.operatDesc = operatDesc;
        this.reqParam = reqParam;
        this.menuId = menuId;
        this.networkIp = networkIp;
        this.groupCode = groupCode;
    }
}
