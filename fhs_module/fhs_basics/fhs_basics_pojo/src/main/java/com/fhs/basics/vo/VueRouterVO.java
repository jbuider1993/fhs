package com.fhs.basics.vo;

import com.fhs.core.base.pojo.BaseObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 左侧菜单路由
 *
 * @Filename: LeftMenu.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@Data
public class VueRouterVO extends BaseObject<VueRouterVO> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 跳转类型
     */
    private String redirect;

    /**
     * 组件类型
     */
    private String component;

    /**
     * 一直展示
     */
    private Boolean alwaysShow = true;

    /**
     * 扩展信息
     */
    private Map<String,String> meta = new HashMap<>();

    /**
     * 子菜单
     */
    private List<VueRouterVO> children = new ArrayList<>();

}
