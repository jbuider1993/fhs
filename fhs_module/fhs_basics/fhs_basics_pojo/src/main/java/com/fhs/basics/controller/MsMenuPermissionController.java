package com.fhs.basics.controller;

import com.fhs.basics.dox.SysMenuDO;
import com.fhs.basics.dox.SysMenuPermissionDO;
import com.fhs.basics.dox.SysMenuPermissionUrlRelaDO;
import com.fhs.basics.service.SysMenuPermissionService;
import com.fhs.basics.vo.SysMenuPermissionUrlRelaVO;
import com.fhs.basics.vo.SysMenuPermissionVO;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.logger.Logger;
import com.fhs.module.base.controller.ModelSuperController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 按钮控制器
 *
 * @author Administrator
 *
 */
@Controller
@RequestMapping("ms/sysMenuPermission")
public class MsMenuPermissionController extends ModelSuperController<SysMenuPermissionVO, SysMenuPermissionDO>
{

    Logger LOG = Logger.getLogger(MsMenuPermissionController.class);

    @Autowired
    SysMenuPermissionService sysMenuPermissionService;

    /**
     * 获取按钮bean数据(根据id)
     *
     * @param request
     * @param reponse
     * @param sysMenuPermission
     * @return
     */
    @RequiresPermissions("sysMenuPermission:see")
    @RequestMapping("toUpdate")
    public void getBeanData(HttpServletRequest request, HttpServletResponse reponse, SysMenuPermissionDO sysMenuPermission)
    {
        SysMenuPermissionDO bean = sysMenuPermissionService.findBean(sysMenuPermission);
        String data = JsonUtils.bean2json(bean);
        super.outWrite(data, reponse);
    }

    /**
     * 查询数据
     *
     * @param request
     * @param response
     * @param adminMenu
     */
    @RequiresPermissions("sysMenuPermission:see")
    @RequestMapping("listData")
    public void listData(HttpServletRequest request, HttpServletResponse response, SysMenuDO adminMenu)
    {
        SysMenuPermissionDO sysMenuPermission = new SysMenuPermissionDO();
        sysMenuPermission.setMenuId(adminMenu.getMenuId());
        int count = sysMenuPermissionService.findCount(sysMenuPermission);
        Map<String, Object> map = super.getPageTurnNum(request);
        map.put("menuId", adminMenu.getMenuId());
        List<SysMenuPermissionVO> dataList = sysMenuPermissionService.findForListFromMap(map);
        super.writeJsonForPager(dataList, count, response);
    }

    /**
     * 根据类型查询数据
     *
     * @param request
     * @param reponse
     * @param sysMenuPermission
     * @return
     */
    @RequiresPermissions("sysMenuPermission:see")
    @RequestMapping("findMapListByType")
    public void findMapListByType(HttpServletRequest request, HttpServletResponse reponse,
        SysMenuPermissionDO sysMenuPermission)
    {
        Map<String, Object> map = super.getPageTurnNum(request);
        map.put("menuId", sysMenuPermission.getMenuId());
        map.put("permissionType", sysMenuPermission.getPermissionType());
        List<Map<String, Object>> dataList = sysMenuPermissionService.findMapListByType(map);
        String data = JsonUtils.list2json(dataList);
        super.outWrite(data, reponse);
    }
    /**
     *
     *一键添加增删改查菜单
     *
     * @param request
     * @paramreponse
     */
    @RequestMapping("addBaseMenuBatch")
    public void addBaseMenuBatch(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> map = super.getParameterMap(request);
        boolean result = sysMenuPermissionService.addBaseMenuBatch(map);
        super.outToClient(result, response);
    }


    /**
     * 根据权限ID查询权限URL关联信息
     * @param sysMenuPermission 权限信息
     * @return Pager
     */
    @RequestMapping("/getUrlByPermissionId")
    @ResponseBody
    public Pager getUrlByPermissionId(SysMenuPermissionDO sysMenuPermission)
    {
        List<SysMenuPermissionUrlRelaVO> urlList = sysMenuPermissionService.getUrlByPermissionId(sysMenuPermission);
        Integer count = sysMenuPermissionService.getUrlCoutByPermissionId(sysMenuPermission);
        return new Pager<>(count, urlList);
    }

    /**
     * 添加权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @param response 相应
     */
    @RequestMapping("/addUrl")
    public void addUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela, HttpServletResponse response)
    {
        boolean result = sysMenuPermissionService.addUrl(sysMenuPermissionUrlRela);
        super.outToClient(result, response);
    }

    /**
     * 修改权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @param response 相应
     */
    @RequestMapping("/updateUrl")
    public void updateUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela, HttpServletResponse response)
    {
        boolean result = sysMenuPermissionService.updateUrl(sysMenuPermissionUrlRela);
        super.outToClient(result, response);
    }

    /**
     * 删除权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @param response 相应
     */
    @RequestMapping("/delUrl")
    public void delUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela, HttpServletResponse response)
    {
        boolean result = sysMenuPermissionService.delUrl(sysMenuPermissionUrlRela);
        super.outToClient(result, response);
    }

}
