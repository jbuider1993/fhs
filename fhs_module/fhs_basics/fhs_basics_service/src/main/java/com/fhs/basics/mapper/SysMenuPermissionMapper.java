package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.SysMenuPermissionDO;
import com.fhs.basics.dox.SysMenuPermissionUrlRelaDO;
import com.fhs.basics.vo.SysMenuPermissionVO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统权限DAO
 * @author jianbo.qin
 *
 */
@MapperDefinition(domainClass = SysMenuPermissionDO.class)
@Repository
public interface SysMenuPermissionMapper extends FhsBaseMapper<SysMenuPermissionDO>
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
    List<SysMenuPermissionDO> readButtonByIds(Map<String, Object> map);

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
    List<SysMenuPermissionUrlRelaDO> getUrlByPermissionId(SysMenuPermissionDO sysMenuPermission);

    /**
     * 根据权限ID查询URL总数
     * @param sysMenuPermission 权限信息
     * @return URL总数
     */
    Integer getUrlCoutByPermissionId(SysMenuPermissionDO sysMenuPermission);

    /**
     * 添加权限URL
     * @param sysMenuPermissionUrlRela 权限URL关联信息
     * @return
     */
    Integer addUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);

    /**
     * 修改权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    Integer updateUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);

    /**
     * 删除权限URL
     * @param sysMenuPermissionUrlRela 权限URL信息
     * @return
     */
    Integer delUrl(SysMenuPermissionUrlRelaDO sysMenuPermissionUrlRela);
}
