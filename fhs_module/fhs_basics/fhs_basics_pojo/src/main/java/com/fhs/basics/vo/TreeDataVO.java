package com.fhs.basics.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色中的菜单树封装类
 *
 * @author jackwong
 */
public class TreeDataVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    /**
     * 是否可读如果可读则设置当前为自己的id
     */
    private String readitem;

    /**
     *
     */
    private String writeitem;

    private String editeitem;

    private String delitem;

    private String elseitem;

    private String parentid;

    /**
     * 是否有button
     */
    private String allitem;

    private Integer isdir = 0;

    private Integer menuLevel = 0;

    private String menuCode;

    private List<TreeDataVO> children = new ArrayList<>();


    public List<TreeDataVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDataVO> children) {
        this.children = children;
    }

    /**
     * 获取 menuCode
     *
     * @return 返回 menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置 menuCode
     *
     * @param对menuCode进行赋值
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getIsdir() {
        return isdir;
    }

    public void setIsdir(Integer isdir) {
        this.isdir = isdir;
    }

    public String getDelitem() {
        return delitem;
    }

    public void setDelitem(String delitem) {
        this.delitem = delitem;
    }

    public String getAllitem() {
        return allitem;
    }

    public void setAllitem(String allitem) {
        this.allitem = allitem;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReaditem() {
        return readitem;
    }

    public void setReaditem(String readitem) {
        this.readitem = readitem;
    }

    public String getWriteitem() {
        return writeitem;
    }

    public void setWriteitem(String writeitem) {
        this.writeitem = writeitem;
    }

    public String getEditeitem() {
        return editeitem;
    }

    public void setEditeitem(String editeitem) {
        this.editeitem = editeitem;
    }

    /**
     * 获取 elseitem
     *
     * @return 返回 elseitem
     */
    public String getElseitem() {
        return elseitem;
    }

    /**
     * 设置 elseitem
     *
     * @param对elseitem进行赋值
     */
    public void setElseitem(String elseitem) {
        this.elseitem = elseitem;
    }

    /**
     * 获取 menuLevel
     *
     * @return 返回 menuLevel
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * 设置 menuLevel
     *
     * @param对menuLevel进行赋值
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

}
