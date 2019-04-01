package com.fhs.ucenter.action;


import com.fhs.base.action.ModelSuperAction;
import com.fhs.ucenter.bean.SysMenuServer;
import com.fhs.ucenter.service.SysMenuServerService;
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
public class MsMenuServerAction extends ModelSuperAction<SysMenuServer> {

    @Autowired
    private SysMenuServerService sysMenuServerService;

    /**
     * 查询服务列表
     * @return
     */
    @RequestMapping("/getData")
    public List<SysMenuServer> getData()
    {
        List<SysMenuServer> list = sysMenuServerService.findForList(new SysMenuServer());
        return list;
    }
}

