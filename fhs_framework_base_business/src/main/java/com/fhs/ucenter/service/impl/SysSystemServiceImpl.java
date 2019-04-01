package com.fhs.ucenter.service.impl;

import com.fhs.common.bean.ComboboxNode;
import com.fhs.ucenter.bean.SysSystem;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.dao.SysSystemDAO;
import com.fhs.ucenter.service.SysSystemService;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fhs.core.db.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author  qixiaobo
 * @version [版本号, 2018-09-26]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
@DataSource("base_business")
public class SysSystemServiceImpl extends BaseServiceImpl<SysSystem> implements SysSystemService {

    @Autowired
    private SysSystemDAO dao;

    @Override
    public List<SysSystem> getSystemList(SysUser sysUser) {
        if(ADMIN == sysUser.getIsAdmin())
        {
            return this.select();
        } else {
            return dao.getSystemList(sysUser);
        }

    }

    @Override
    public List<ComboboxNode> getSystemComBoxData() {
        List<SysSystem> sysSystems = this.select();
        List<ComboboxNode> nodeList = new ArrayList<>();
        ComboboxNode node = null;
        for(SysSystem sysSystem : sysSystems)
        {
            node = new ComboboxNode(sysSystem.getId(), sysSystem.getName());
            nodeList.add(node);
        }
        return nodeList;
    }

}
