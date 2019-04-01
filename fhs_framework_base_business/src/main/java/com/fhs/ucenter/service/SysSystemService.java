package com.fhs.ucenter.service;

import com.fhs.common.bean.ComboboxNode;
import com.fhs.ucenter.bean.SysSystem;
import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.SysUser;

import java.util.List;

/**
 * @Description:
 * @author  qixiaobo
 * @version [版本号, 2018-09-26]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
public interface SysSystemService extends BaseService<SysSystem> {

    /**
     * 超管用户
     */
    public static final int ADMIN = 1;

    /**
     * 获取全部商户的combbox
     * @return
     */
    List<ComboboxNode> getSystemComBoxData();

    /**
     * 查询当前登录用户拥有权限的子系统列表
     * @param sysUser 登录用户
     * @return 子系统列表
     */
    List<SysSystem> getSystemList(SysUser sysUser);
}
