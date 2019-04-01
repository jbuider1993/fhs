package com.fhs.ucenter.api;

import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.FeignSysMenuApiService;
import com.fhs.ucenter.api.vo.SysMenuVo;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单API接口
 *
 * @Filename: SysMenuApiServiceCloud.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@RestController
@RequestMapping("api/sysMenu")
@DataSource("pagex/ucenter")
public class SysMenuApiServiceCloud implements FeignSysMenuApiService {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询id,name,namespace列表
     */
    @GetMapping("/findIdAndNameAndNamespaceList")
    @Override
    public HttpResult<List<SysMenuVo>> findIdAndNameAndNamespaceList() {
        List<SysMenu> idAndNameAndNamespaceList = sysMenuService.findIdAndNameAndNamespaceList();
        final List<SysMenuVo> resultList = new ArrayList<>();
        idAndNameAndNamespaceList.forEach(menu->
        {
            SysMenuVo tempVo = new SysMenuVo();
            BeanUtils.copyProperties(menu,tempVo);
            resultList.add(tempVo);
        });
        return HttpResult.success(resultList);
    }
}
