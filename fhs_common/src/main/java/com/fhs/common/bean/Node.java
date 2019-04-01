package com.fhs.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * easyui tree node
 *
 * @Filename: Node.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: wanglei@sxpartner.com
 * @History:
 * 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class Node
{
    public Node(String id, String text, String state, boolean checked,int index)
    {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.checked = checked;
        this.index = index;
    }

    /**
     * id
     */
    private String id;

    /**
     *级别
     */
    private int index;

    /**
     * tree显示的字符串
     */
    private String text;

    /**
     * 状态 默认是open
     */
    private String state;

    /**
     * 默认选中
     */
    private boolean checked;

    /**
     * 子枝叶
     */
    private List<Node> children = new ArrayList<>();


    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public List<Node> getChildren()
    {
        return children;
    }

    public void setChildren(List<Node> children)
    {
        this.children = children;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }




}
