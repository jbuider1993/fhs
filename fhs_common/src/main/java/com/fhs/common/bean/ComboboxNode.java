package com.fhs.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * easyui combobox node.
 *
 * @Filename: Node.java
 * @description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qixiaobo@sxpartner.com
 * @History: 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class ComboboxNode
{

    /** id. */
    private String id;

    /** 显示. */
    private String text;

    /** 微信orgid. */
    private String wxOrgId;

    /** 父id. */
    private String parentId;

    private String classifyName;
    /** 状态. */
    private String state = "closed";

    /** The children. */
    private List<ComboboxNode> children = new ArrayList<>();

    /**
     * Instantiates a new combobox node.
     *
     * @param id the id
     * @param text the text
     */
    public ComboboxNode(String id, String text)
    {
        super();
        this.id = id;
        this.text = text;
        
        
    }

    /**
     * Instantiates a new combobox node.
     */
    public ComboboxNode() {

    }

    /**
     * 获取 id.
     *
     * @return bean的 id
     */
    public String getId()
    {
        return id;
    }

    /**
     * 设置 id.
     *
     * @param id 一个新的 id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * 获取 显示.
     *
     * @return bean的 显示
     */
    public String getText()
    {
        return text;
    }

    /**
     * 设置 显示.
     *
     * @param text 一个新的 显示
     */
    public void setText(String text)
    {
        this.text = text;
    }

	/**
     * 获取 children.
     *
     * @return children
     */
	public List<ComboboxNode> getChildren() {
		return children;
	}

	/**
     * 设置 children.
     *
     * @param children 新的 children
     */
	public void setChildren(List<ComboboxNode> children) {
		this.children = children;
	}

    /**
     * 获取 父id.
     *
     * @return bean的 父id
     */
    public String getParentId()
    {
        return parentId;
    }

    /**
     * 设置 父id.
     *
     * @param parentId 一个新的 父id
     */
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    /**
     * 获取 微信orgid.
     *
     * @return bean的 微信orgid
     */
    public String getWxOrgId()
    {
        return wxOrgId;
    }

    /**
     * 设置 微信orgid.
     *
     * @param wxOrgId 一个新的 微信orgid
     */
    public void setWxOrgId(String wxOrgId)
    {
        this.wxOrgId = wxOrgId;
    }

    /**
     * 获取 状态.
     *
     * @return bean的 状态
     */
    public String getState()
    {
        return state;
    }

    /**
     * 设置 状态.
     *
     * @param state 一个新的 状态
     */
    public void setState(String state)
    {
        this.state = state;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
