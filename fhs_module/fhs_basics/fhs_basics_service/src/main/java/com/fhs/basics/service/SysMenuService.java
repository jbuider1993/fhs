package com.fhs.basics.service;

import com.fhs.basics.dox.SysMenuDO;
import com.fhs.basics.vo.SysMenuVO;
import com.fhs.basics.vo.TreeDataVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 菜单业务接口，实现增删改查等业务
 * @author Administrator
 *
 */
public interface SysMenuService extends BaseService<SysMenuVO, SysMenuDO>
{

    /**
     *不需要显示的菜单
     */
    int NOT_SHOW = 1;

    /**
     * 租户
     */
    int MENU_TYPE_TENANT=1;

    /**
     * 根据父Id获取树集合
     * @param parentId 条件列表
     * @return
     */
    List<TreeModelVO> findMenuTrees(String parentId);
    /**
     * 获取菜单树集合并组建json
     * @param menuType 根据菜单类型获取菜单树
     * @param groupCode 集团编码
     * @return
     */
    List<TreeDataVO>  findMenuTreeToJson(Integer menuType, String groupCode);

    /**
     * 获取菜单树集合并组建json
     * @param menuId 父节点id
     * @return
     */
    List<TreeDataVO>  findMenuTreeToJson(String menuId);

    /**
     * 通过菜单Id删除权限
     * @param menu
     * @return
     */
    boolean deleteButton(SysMenuDO menu);


    /**
     * 获取服务器列表
     * @return
     */
    List<Map<String,Object>> searchServer();

    /**
     * 修改当前节点的子节点状态
     *
     * @param menu
     * @return
     */
    boolean updateDisable(SysMenuDO menu);

    /**
     * 查询id,name,namespace列表
     */
    List<SysMenuVO> findIdAndNameAndNamespaceList();
}
