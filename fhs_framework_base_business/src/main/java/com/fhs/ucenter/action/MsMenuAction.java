package com.fhs.ucenter.action;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.bean.TreeData;
import com.fhs.ucenter.bean.TreeModel;
import com.fhs.ucenter.service.SysMenuService;
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
 * 系统菜单Action
 *
 * @author jianbo.qin
 *
 */
@Controller
@RequestMapping("ms/sysMenu")
public class MsMenuAction extends ModelSuperAction<SysMenu>
{

    private static final Logger LOG = Logger.getLogger(MsMenuAction.class);

    @Autowired
    SysMenuService sysMenuService;

    /**
     * 获取菜单树结构json字符串对象 菜单管理左侧树形结构
     *
     * @param request
     * @param response
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("getMenuTreesData")
    @ResponseBody
    public List<TreeModel> getOrgStrutureTreesData(HttpServletRequest request, HttpServletResponse response)
    {
        List<TreeModel> menuTrees = sysMenuService.findMenuTrees(request.getParameter("id"));
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
    @ResponseBody
    public HttpResult addMenu(HttpServletRequest request, HttpServletResponse response, SysMenu adminMenu)
    {
        try
        {
            String url = adminMenu.getMenuUrl();
            if (!CheckUtils.isNullOrEmpty(url))
            {
                if (url.indexOf("\\") != -1)
                {
                    url = url.replaceAll("\\\\", "/");
                    adminMenu.setMenuUrl(url);
                }
            }
            sysMenuService.add(adminMenu);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success(true);
    }

    /**
     * 获取bean数据(根据id)
     *
     * @param request
     * @param reponse
     * @param adminMenu
     * @return
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("toUpdate")
    public void getBeanData(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {
        SysMenu bean = sysMenuService.findBean(adminMenu);
        String data = JsonUtils.bean2json(bean);
        super.outWrite(data, reponse);
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
    @ResponseBody
    public HttpResult updateMenu(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {
        try
        {
            String url = adminMenu.getMenuUrl();
            if (!CheckUtils.isNullOrEmpty(url))
            {
                if (url.indexOf("\\") != -1)
                {
                    url = url.replaceAll("\\\\", "/");
                    adminMenu.setMenuUrl(url);
                }

            }
            sysMenuService.update(adminMenu);
        }
        catch (Exception e)
        {
            return HttpResult.error(e);
        }
        return HttpResult.success();
    }

    /**
     * 查询菜单数据
     *
     * @param request
     * @param reponse
     * @param adminMenu
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("listData")
    public void listData(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {

        Map<String, Object> map = super.getPageTurnNum(request);
        map.put("menuType", adminMenu.getMenuType());
        map.put("menuId", adminMenu.getMenuId());
        int count = sysMenuService.findCount(adminMenu);
        List<SysMenu> dataList = sysMenuService.findForListFromMap(map);
        super.writeJsonForPager(dataList, count, reponse);
    }

    /**
     * 查询根节点数据，添加角色easyui-treegrid使用
     *
     * @paramrequst response adminMenu
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("findMenuRootTrees")
    public void findMenuRootTrees(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {
        Integer menuType = 0;// 平台菜单
        List<TreeData> treeList = sysMenuService.findMenuTreeToJson(menuType);
        String jsonTree = JsonUtils.list2json(treeList);
        super.outWrite(jsonTree, reponse);
    }

    /**
     * 查询根节点数据，添加角色easyui-treegrid使用
     *
     * @paramrequst response adminMenu
     */
    @RequiresPermissions("sysMenu:see")
    @RequestMapping("findMenuParkTrees")
    public void findMenuParkTrees(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {
        String menuType = request.getParameter("menuType"); // 0-运营菜单 1-园区菜单
        List<TreeData> treeList = sysMenuService.findMenuTreeToJson(menuType);
        String jsonTree = JsonUtils.list2json(treeList);
        super.outWrite(jsonTree, reponse);
    }

    /**
     * 查询服务器
     *
     * @paramrequst response adminMenu
     */
    @RequestMapping("searchServer")
    public void searchServer(HttpServletRequest request, HttpServletResponse reponse, SysMenu adminMenu)
    {
        List<Map<String, Object>> dataList = sysMenuService.searchServer();
        String jsonTree = JsonUtils.list2json(dataList);
        super.outWrite(jsonTree, reponse);
    }

}
