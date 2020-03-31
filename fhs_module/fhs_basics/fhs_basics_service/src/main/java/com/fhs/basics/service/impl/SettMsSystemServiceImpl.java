package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SettMsSystemDO;
import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.basics.mapper.SettMsSystemMapper;
import com.fhs.basics.service.SettMsSystemService;
import com.fhs.basics.vo.ComboboxNodeVO;
import com.fhs.basics.vo.SettMsSystemVO;
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
public class SettMsSystemServiceImpl extends BaseServiceImpl<SettMsSystemVO, SettMsSystemDO> implements SettMsSystemService {

    @Autowired
    private SettMsSystemMapper mapper;

    @Override
    public List<SettMsSystemVO> getSystemList(UcenterMsUserDO sysUser) {
        if (ADMIN == sysUser.getIsAdmin()) {
            return this.select();
        } else {
            return dos2vos(mapper.getSystemList(sysUser));
        }

    }

    @Override
    public List<ComboboxNodeVO> getSystemComBoxData() {
        List<SettMsSystemVO> sysSystems = this.select();
        List<ComboboxNodeVO> nodeList = new ArrayList<>();
        ComboboxNodeVO node = null;
        for (SettMsSystemVO sysSystem : sysSystems) {
            node = new ComboboxNodeVO(sysSystem.getId(), sysSystem.getName());
            nodeList.add(node);
        }
        return nodeList;
    }

}
