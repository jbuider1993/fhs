package com.fhs.basics.dox;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import com.fhs.core.valid.group.Add;
import com.fhs.core.valid.group.Delete;
import com.fhs.core.valid.group.Update;
import com.mybatis.jpa.annotation.Like;
import com.fhs.common.constant.Constant;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 省市区字典
 *
 * @Filename: AreaDO.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@Entity
@Table(name = "t_service_area")
public class AreaDO extends BaseDO<AreaDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 区域主键
     */
    @NotNull(message = "area的id字段 不可为null ", groups = {Update.class, Delete.class})
    @Max(message = "area的id超过int最大值", value = 2147483647, groups = {Delete.class, Update.class})
    @Min(message = "area的id小于int最大值", value = -2147483648, groups = {Delete.class, Update.class})
    @Id
    @Column(name = "id", nullable = false, length = 10)
    @Like
    private Integer id;

    /**
     * 区域名称
     */
    @Length(message = "area的areaName字段的长度最大为16", groups = {Add.class, Update.class}, max = 16, min = 0)
    @Column(name = "area_name", nullable = true, length = 16)
    @Like
    private String areaName;

    /**
     * 区域代码
     */
    @Length(message = "area的areaCode字段的长度最大为128", groups = {Add.class, Update.class}, max = 128, min = 0)
    @Column(name = "area_code", nullable = true, length = 128)
    @Like
    private String areaCode;

    /**
     * 区域简称
     */
    @Length(message = "area的areaShort字段的长度最大为32", groups = {Add.class, Update.class}, max = 32, min = 0)
    @Column(name = "area_short", nullable = true, length = 32)
    private String areaShort;

    /**
     * 是否热门(0:否、1:是)
     */
    @Length(message = "area的areaIsHot字段的长度最大为1", groups = {Add.class, Update.class}, max = 1, min = 0)
    @Column(name = "area_is_hot", nullable = true, length = 1)
    private String areaIsHot;

    /**
     * 区域序列
     */
    @Max(message = "{area的areaSequence字段大于int最大值}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{area的areaSequence字段小于int小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "area_sequence", nullable = true, length = 10)
    private Integer areaSequence;

    /**
     * 上级主键
     */
    @Max(message = "{area的areaParentId字段大于int最大值}", value = 2147483647, groups = {Add.class, Update.class})
    @Min(message = "{area的areaParentId字段小于int小值", value = -2147483648, groups = {Add.class, Update.class})
    @Column(name = "area_parent_id", nullable = true, length = 10)
    @Trans(type = BaseTransConstant.AREA)
    private Integer areaParentId;

    /**
     * 初始时间
     */
    @Column(name = "init_date", nullable = true, length = 32)
    private String initDate;

    /**
     * 初始地址
     */
    @Length(message = "area的initAddr字段的长度最大为16", groups = {Add.class, Update.class}, max = 16, min = 0)
    @Column(name = "init_addr", nullable = true, length = 16)
    private String initAddr;

    /**
     * 给区域主键赋值
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取区域主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 给区域名称赋值
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取区域名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 给区域代码赋值
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取区域代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 给区域简称赋值
     */
    public void setAreaShort(String areaShort) {
        this.areaShort = areaShort;
    }

    /**
     * 获取区域简称
     */
    public String getAreaShort() {
        return areaShort;
    }

    /**
     * 给是否热门(0:否、1:是)赋值
     */
    public void setAreaIsHot(String areaIsHot) {
        this.areaIsHot = areaIsHot;
    }

    /**
     * 获取是否热门(0:否、1:是)
     */
    public String getAreaIsHot() {
        return areaIsHot;
    }

    /**
     * 给区域序列赋值
     */
    public void setAreaSequence(Integer areaSequence) {
        this.areaSequence = areaSequence;
    }

    /**
     * 获取区域序列
     */
    public Integer getAreaSequence() {
        return areaSequence;
    }

    /**
     * 给上级主键赋值
     */
    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    /**
     * 获取上级主键
     */
    public Integer getAreaParentId() {
        return areaParentId;
    }

    /**
     * 获取初始时间
     */
    public String getInitDate() {
        return initDate;
    }

    /**
     * 给初始时间赋值
     */
    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    /**
     * 给初始地址赋值
     */
    public void setInitAddr(String initAddr) {
        this.initAddr = initAddr;
    }

    /**
     * 获取初始地址
     */
    public String getInitAddr() {
        return initAddr;
    }

}
