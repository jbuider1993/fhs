package com.fhs.basics.mapper;

import com.fhs.basics.dox.SettMsMenuDO;
import com.fhs.basics.vo.TreeDataVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单DAO
 * @author jianbo.qin
 */
@Repository
@MapperDefinition(domainClass = SettMsMenuDO.class)
public interface SettMsMenuMapper extends FhsBaseMapper<SettMsMenuDO>
{
    /**
     * 根据父Id获取树集合
     *
     * @param paramMap 包含parentId 父Id
     * @return
     */
    List<TreeModelVO> findMenuTrees(Map<String, Object> paramMap);

    /**
     * 获取菜单根目录
     *
     * @return
     */
    List<TreeDataVO> findMenuRootTrees();

    /**
     * 根据MenuType查询菜单集合
     * @param paramMap
     * @return
     */
    List<TreeDataVO> findMenuTreesByMenuType(Map<String, Object> paramMap);

    /**
     * 更加父id获取子节点数据
     *
     * @param map
     * @return
     */
    List<TreeDataVO> findMenuTreesByParentId(Map<String, Object> map);

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
    int deleteButton(SettMsMenuDO menu);

    /**
     * 查询所有菜单权限
     *
     * @param paramMap
     * @return
     */
    List<SettMsMenuDO> findForAllList(Map<String, Object> paramMap);

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
    int updateDisable(SettMsMenuDO menu);

    /**
     * 找到所有的菜单id不包括root
     *
     * @param paramMap
     * @return
     */
    List<SettMsMenuDO> findAllIdsExcept(Map<String, Object> paramMap);

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
    List<SettMsMenuDO> findIdAndNameAndNamespaceList();

}
