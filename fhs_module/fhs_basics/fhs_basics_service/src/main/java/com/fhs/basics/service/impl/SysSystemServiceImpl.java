package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SysSystemDO;
import com.fhs.basics.dox.SysUserDO;
import com.fhs.basics.mapper.SysSystemMapper;
import com.fhs.basics.service.SysSystemService;
import com.fhs.basics.vo.ComboboxNodeVO;
import com.fhs.basics.vo.SysSystemVO;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-26]
 * @Description:
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
@DataSource("base_business")
public class SysSystemServiceImpl extends BaseServiceImpl<SysSystemVO, SysSystemDO> implements SysSystemService {

    @Autowired
    private SysSystemMapper mapper;

    @Override
    public List<SysSystemVO> getSystemList(SysUserDO sysUser) {
        if (ADMIN == sysUser.getIsAdmin()) {
            return this.select();
        } else {
            return dos2vos(mapper.getSystemList(sysUser));
        }

    }

    @Override
    public List<ComboboxNodeVO> getSystemComBoxData() {
        List<SysSystemVO> sysSystems = this.select();
        List<ComboboxNodeVO> nodeList = new ArrayList<>();
        ComboboxNodeVO node = null;
        for (SysSystemVO sysSystem : sysSystems) {
            node = new ComboboxNodeVO(sysSystem.getId(), sysSystem.getName());
            nodeList.add(node);
        }
        return nodeList;
    }

}
