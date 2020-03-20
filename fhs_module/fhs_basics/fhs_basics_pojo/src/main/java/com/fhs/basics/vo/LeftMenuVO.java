package com.fhs.basics.vo;

import com.fhs.core.base.pojo.BaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单.
 *
 * @Filename: LeftMenu.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
public class LeftMenuVO extends BaseObject<LeftMenuVO> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id.
     */
    private Integer id;

    /**
     * 菜单名称.
     */
    private String name;

    /**
     * url.
     */
    private String url;

    /**
     * 排序.
     */
    private int orderIndex;

    /**
     * 子菜单.
     */
    private List<LeftMenuVO> sonMenu = new ArrayList<>();

    /**
     * 菜单服务器id
     */
    private int menuServer;

    private String image;

    private String serverUrl;

    /**
     * 获取 id.
     *
     * @return bean的 id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 id.
     *
     * @param id 一个新的 id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 菜单名称.
     *
     * @return bean的 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 菜单名称.
     *
     * @param name 一个新的 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 url.
     *
     * @return bean的 url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 url.
     *
     * @param url 一个新的 url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 排序.
     *
     * @return bean的 排序
     */
    public int getOrderIndex() {
        return orderIndex;
    }

    /**
     * 设置 排序.
     *
     * @param orderIndex 一个新的 排序
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * 获取 子菜单.
     *
     * @return bean的 子菜单
     */
    public List<LeftMenuVO> getSonMenu() {
        return sonMenu;
    }

    /**
     * 设置 子菜单.
     *
     * @param sonMenu 一个新的 子菜单
     */
    public void setSonMenu(List<LeftMenuVO> sonMenu) {
        this.sonMenu = sonMenu;
    }

    public int getMenuServer() {
        return menuServer;
    }

    public void setMenuServer(int menuServer) {
        this.menuServer = menuServer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
