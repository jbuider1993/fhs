package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.bean.TreeData;
import com.fhs.ucenter.bean.TreeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单DAO
 * @author jianbo.qin
 *
 */
@MapperDefinition(domainClass = SysMenu.class)

public interface SysMenuDAO extends BaseDao<SysMenu>
{
    /**
     * 根据父Id获取树集合
     *
     * @param parentId 父Id
     * @return
     */
    List<TreeModel> findMenuTrees(Map<String, Object> map);

    /**
     * 获取菜单根目录
     *
     * @return
     */
    List<TreeData> findMenuRootTrees();

    /**
     * 根据MenuType查询菜单集合
     * @param paramMap
     * @return
     */
    List<TreeData> findMenuTreesByMenuType(Map<String,Object> paramMap);

    /**
     * 更加父id获取子节点数据
     *
     * @param map
     * @return
     */
    List<TreeData> findMenuTreesByParentId(Map<String, Object> map);

    /**
     * 监测当前ID是否有子节点
     *
     * @param map
     * @return
     */
    int isExit(Map<String, Object> map);

    /**
     * 通过菜单Id删除权限
     *
     * @param menu
     * @return
     */
    int deleteButton(SysMenu menu);

    /**
     * 查询所有菜单权限
     *
     * @param menu
     * @return
     */
    List<SysMenu> findForAllList(Map<String, Object> paramMap);

    /**
     * 获取服务器列表
     *
     * @return
     */
    List<Map<String, Object>> searchServer();

    /**
     * 修改当前节点的子节点状态
     *
     * @param menu
     * @return
     */
    int updateDisable(SysMenu menu);

    /**
     * 找到所有的菜单id不包括root
     *
     * @param paramMap
     * @return
     */
    List<SysMenu> findAllIdsExcept(Map<String, Object> paramMap);

    /**
     * 根据id 查询菜单url
     */
    Map<String, Object> getUrlById(Map<String, Object> parmMap);

    /**
     * 查询菜单是否配置在主页显示
     */
    Map<String, Object> getConfigurationHomeMenu(Map<String, Object> paramMap);

    /**
     * 查询id,name,namespace列表
     */
    List<SysMenu> findIdAndNameAndNamespaceList();

}
