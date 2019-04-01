package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.SysMenuPermissionUrlRela;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统权限DAO
 * @author jianbo.qin
 *
 */
@MapperDefinition(domainClass = SysMenuPermission.class)
@Repository
public interface SysMenuPermissionDAO extends BaseDao<SysMenuPermission>
{
    /**
     * 根据按钮类型获取按钮集合
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapListByType(Map<String, Object> map);

    /**
     * 通过IDs获取权限集合
     *
     * @param map
     * @return
     */
    List<SysMenuPermission> readButtonByIds(Map<String, Object> map);

    /**
     *
     * 一键添加增删改查菜单
     *
     * @param map
     * @return
     */
    int addBaseMenuBatch(Map<String, Object> map);

    /**
     *
     * 查询所有权限，如果传了groupCode，则获取该集团所有应用的所有权限
     *
     * @param paramMap(menuType 0物业菜单，1社区菜单)
     * @return
     */
    List<Map<String, Object>> getButtonRoleCodeMap(Map<String, Object> paramMap);

    /**
     *
     * <获取有权限的buttonId>
     *
     * @param paramMap
     * @return 返回有权限的按钮id
     */
    List<Map<String, Object>> getButtonIdByAccess(Map<String, Object> paramMap);


    /**
     * 根据权限ID查询权限URL关联信息
     * @param sysMenuPermission 权限信息
     * @return URL列表
     */
    List<SysMenuPermissionUrlRela> getUrlByPermissionId(SysMenuPermission sysMenuPermission);

    /**
     * 根据权限ID查询URL总数
     * @param sysMenuPermission 权限信息
     * @return URL总数
     */
    Integer getUrlCoutByPermissionId(SysMenuPermission sysMenuPermission);

    /**
     * 添加权限URL
     * @param sysMenuPermissionUrlRela 权限URL关联信息
     * @return
     */
    Integer addUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);

    /**
     * 修改权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    Integer updateUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);

    /**
     * 删除权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    Integer delUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);
}
