package com.fhs.basics.controller;

import com.fhs.basics.dox.SettMsMenuPermissionDO;
import com.fhs.basics.service.SettMsMenuPermissionService;
import com.fhs.basics.vo.SettMsMenuPermissionVO;
import com.fhs.module.base.controller.ModelSuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 按钮控制器
 *
 * @author Administrator
 */
@RestController
@RequestMapping("ms/sysMenuPermission")
public class SettMsMenuPermissionController extends ModelSuperController<SettMsMenuPermissionVO, SettMsMenuPermissionDO> {


    @Autowired
    private SettMsMenuPermissionService sysMenuPermissionService;

    /**
     * 一键添加增删改查菜单
     *
     * @param request
     * @paramreponse
     */
    @RequestMapping("addBaseMenuBatch")
    public void addBaseMenuBatch(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParameterMap();
        boolean result = sysMenuPermissionService.addBaseMenuBatch(map);
        super.outToClient(result);
    }

}
