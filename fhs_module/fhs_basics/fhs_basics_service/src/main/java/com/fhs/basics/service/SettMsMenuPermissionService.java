package com.fhs.basics.service;

import com.fhs.basics.dox.SettMsMenuPermissionDO;
import com.fhs.basics.dox.SettMsMenuPermissionUrlRelaDO;
import com.fhs.basics.vo.SettMsMenuPermissionUrlRelaVO;
import com.fhs.basics.vo.SettMsMenuPermissionVO;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单和权限
 */
public interface SettMsMenuPermissionService extends BaseService<SettMsMenuPermissionVO, SettMsMenuPermissionDO> {
    /**
     * 根据按钮类型获取按钮集合
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> findMapListByType(Map<String, Object> map);

    /**
     * 一键添加增删改查菜单
     *
     * @param map
     * @return
     */
    boolean addBaseMenuBatch(Map<String, Object> map);

    /**
     * 根据权限ID查询权限URL关联信息
     *
     * @param sysMenuPermission 权限信息
     * @return SysMenuPermissionUrlRelaVO列表
     */
    List<SettMsMenuPermissionUrlRelaVO> getUrlByPermissionId(SettMsMenuPermissionDO sysMenuPermission);

    /**
     * 根据权限ID查询URL总数
     *
     * @param sysMenuPermission 权限信息
     * @return URL总数
     */
    Integer getUrlCoutByPermissionId(SettMsMenuPermissionDO sysMenuPermission);

    /**
     * 添加权限URL
     *
     * @param sysMenuPermissionUrlRela 权限URL关联信息
     * @return
     */
    boolean addUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);

    /**
     * 修改权限URL
     *
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    boolean updateUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);

    /**
     * 删除权限URL
     *
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    boolean delUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);
}
