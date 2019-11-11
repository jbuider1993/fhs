package com.fhs.workflow.bean;

import java.io.Serializable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.mybatis.jpa.annotation.*;
import com.fhs.core.group.*;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;

import javax.validation.constraints.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fhs.core.base.bean.BaseDO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 流程列表-xml(FlowJbpmXml)实体类
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:29:04
 */

@Data
@Builder
@TableName("t_flow_jbpm_xml")
public class FlowJbpmXml extends BaseDO<FlowJbpmXml> {
    private static final long serialVersionUID = -26479945401118903L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    //流程名称
    @NotEmpty
    @NotNull(message = "流程名称字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "流程名称字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("name")
    private String name;

    //流程key
    @NotEmpty
    @NotNull(message = "流程key字段不可为null", groups = {Update.class, Delete.class})
    @Length(message = "流程key字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("key")
    private String key;

    //表单在哪个服务器上
    @NotNull(message = "表单在哪个服务器上字段不可为null", groups = {Update.class, Delete.class})
    @TableField("server")
    private Integer server;

    //表单是否是pagex实现
    @NotNull(message = "表单是否是pagex实现字段不可为null", groups = {Update.class, Delete.class})
    @TableField("is_pagex")
    private Integer isPagex;

    //如果不是pagex实现的话表单url是多少
    @Length(message = "如果不是pagex实现的话表单url是多少字段的长度最大为255", groups = {Add.class, Update.class}, max = 255)
    @TableField("uri")
    private String uri;

    //如果是pagex的话namespace是多少
    @Length(message = "如果是pagex的话namespace是多少字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("namespace")
    private String namespace;

    //0草稿 1已发布 2 已禁用
    @NotNull(message = "0草稿 1已发布 2 已禁用字段不可为null", groups = {Update.class, Delete.class})
    @TableField("status")
    private Integer status;

    // xml
    @TableField("xml")
    private String xml;

    // xml图片
    @TableField("img")
    private String img;


    public FlowJbpmXml() {
    }

    public FlowJbpmXml(String id, String name, String key, Integer server, Integer isPagex, String uri, String namespace, Integer status, String xml) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.server = server;
        this.isPagex = isPagex;
        this.uri = uri;
        this.namespace = namespace;
        this.status = status;
        this.xml = xml;
    }
}