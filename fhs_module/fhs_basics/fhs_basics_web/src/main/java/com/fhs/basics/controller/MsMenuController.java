package com.fhs.basics.controller;

;
import com.fhs.basics.dox.SysMenuDO;
import com.fhs.basics.service.SysMenuService;
import com.fhs.basics.vo.SysMenuVO;
import com.fhs.basics.vo.TreeDataVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import com.fhs.module.base.controller.ModelSuperController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 系统菜单Action
 *
 * @author jianbo.qin
 */
@RestController
@RequestMapping("ms/sysMenu")
public class MsMenuController extends ModelSuperController<SysMenuVO, SysMenuDO> {

    private static final Logger LOG = Logger.getLogger(MsMenuController.class);

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单树结构json字符串对象 菜单管理左侧树形结构
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("getMenuTreesData")
    public List<TreeModelVO> getOrgStrutureTreesData(HttpServletRequest request, HttpServletResponse response) {
        List<TreeModelVO> menuTrees = sysMenuService.findMenuTrees(request.getParameter("id"));
        return menuTrees;
    }

    /**
     * 添加菜单
     *
     * @param request
     * @param response
     * @param adminMenu
     */
    @RequiresPermissions("sysMenu:add")
    @RequestMapping("addMenu")
    public HttpResult addMenu(HttpServletRequest request, HttpServletResponse response, SysMenuVO adminMenu) {
        try {
            String url = adminMenu.getMenuUrl();
            if (!CheckUtils.isNullOrEmpty(url)) {
                if (url.indexOf("\\") != -1) {
                    url = url.replaceAll("\\\\", "/");
                    adminMenu.setMenuUrl(url);
                }
            }
            sysMenuService.add(adminMenu);
        } catch (Exception e) {
            return HttpResult.error(e);
        }
        return HttpResult.success(true);
    }


    /**
     * 更新菜单信息
     *
     * @param request
     * @param reponse
     * @param adminMenu
     */
    @RequiresPermissions("sysMenu:update")
    @RequestMapping("updateMenu")
    public HttpResult<Boolean> updateMenu(HttpServletRequest request, HttpServletResponse reponse, SysMenuVO adminMenu) {
        String url = adminMenu.getMenuUrl();
        if (!CheckUtils.isNullOrEmpty(url)) {
            if (url.indexOf("\\") != -1) {
                url = url.replaceAll("\\\\", "/");
                adminMenu.setMenuUrl(url);
            }

        }
        sysMenuService.update(adminMenu);
        return HttpResult.success(true);
    }


    /**
     * 查询根节点数据，添加角色easyui-treegrid使用
     *
     * @paramrequst response adminMenu
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("findMenuRootTrees")
    public void findMenuRootTrees(HttpServletRequest request, HttpServletResponse reponse, SysMenuVO adminMenu) {
        Integer menuType = 0;// 平台菜单
        List<TreeDataVO> treeList = sysMenuService.findMenuTreeToJson(menuType, super.getSessionuser().getGroupCode());
        String jsonTree = JsonUtils.list2json(treeList);
        super.outWrite(jsonTree);
    }

    /**
     * 查询根节点数据，添加角色easyui-treegrid使用
     *
     * @paramrequst response adminMenu
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("findMenuParkTrees")
    public void findMenuParkTrees(HttpServletRequest request, HttpServletResponse reponse, SysMenuVO adminMenu) {
        String menuType = request.getParameter("menuType"); // 0-运营菜单 1-园区菜单
        List<TreeDataVO> treeList = sysMenuService.findMenuTreeToJson(menuType);
        String jsonTree = JsonUtils.list2json(treeList);
        super.outWrite(jsonTree);
    }
}
