package com.fhs.basics.service;

import com.fhs.basics.dox.SysMenuPermissionDO;
import com.fhs.basics.dox.SysMenuPermissionUrlRelaDO;
import com.fhs.basics.vo.SysMenuPermissionUrlRelaVO;
import com.fhs.basics.vo.SysMenuPermissionVO;
import com.fhs.core.base.service.BaseService;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单和权限
 */
public interface SysMenuPermissionService extends BaseService<SysMenuPermissionVO,SysMenuPermissionDO>
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
     * @return SysMenuPermissionUrlRelaVO列表
     */
    List<SysMenuPermissionUrlRelaVO> getUrlByPermissionId(SysMenuPermissionDO sysMenuPermission);

    /**
     * 根据权限ID查询URL总数
     * @param sysMenuPermission 权限信息
     * @return URL总数
     */
    Integer getUrlCoutByPermissionId(SysMenuPermissionDO sysMenuPermission);

    /**
     * 添加权限URL
     * @param SysMenuPermissionUrlRelaVO 权限URL关联信息
     * @return
     */
    boolean addUrl(SysMenuPermissionUrlRelaDO SysMenuPermissionUrlRelaVO);

    /**
     * 修改权限URL
     * @param SysMenuPermissionUrlRelaVO 权限URL信息
     * @return
     */
    boolean updateUrl(SysMenuPermissionUrlRelaDO SysMenuPermissionUrlRelaVO);

    /**
     * 删除权限URL
     * @param SysMenuPermissionUrlRelaVO 权限URL信息
     * @return
     */
    boolean delUrl(SysMenuPermissionUrlRelaDO SysMenuPermissionUrlRelaVO);
}
