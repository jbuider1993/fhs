package com.fhs.ucenter.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.SysMenuPermissionUrlRela;

import java.util.List;
import java.util.Map;

public interface SysMenuPermissionService extends BaseService<SysMenuPermission>
{
    /**
     * 根据按钮类型获取按钮集合
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapListByType(Map<String, Object> map);

    /**
     *
     * 一键添加增删改查菜单
     *
     * @param map
     * @return
     */
    boolean addBaseMenuBatch(Map<String, Object> map);

    /**
     * 根据权限ID查询权限URL关联信息
     * @param sysMenuPermission 权限信息
     * @return SysMenuPermissionUrlRela列表
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
    boolean addUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);

    /**
     * 修改权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    boolean updateUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);

    /**
     * 删除权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    boolean delUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela);
}
