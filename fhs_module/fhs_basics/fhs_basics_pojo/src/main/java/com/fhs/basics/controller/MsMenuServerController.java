package com.fhs.basics.controller;

import com.fhs.basics.dox.SysMenuServerDO;
import com.fhs.basics.service.SysMenuServerService;
import com.fhs.basics.vo.SysMenuServerVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author  jianbo.qin
 * @version [版本号, 2018-06-01]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 */
@RestController
@RequestMapping("ms/menuServer")
public class MsMenuServerController extends ModelSuperController<SysMenuServerVO, SysMenuServerDO> {

    @Autowired
    private SysMenuServerService sysMenuServerService;

    /**
     * 查询服务列表
     * @return
     */
    @RequestMapping("/getData")
    public List<SysMenuServerVO> getData()
    {
        List<SysMenuServerVO> list = sysMenuServerService.findForList(new SysMenuServerDO());
        return list;
    }
}

