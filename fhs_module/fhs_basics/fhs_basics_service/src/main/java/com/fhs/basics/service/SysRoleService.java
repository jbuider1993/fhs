package com.fhs.basics.service;

import com.fhs.basics.dox.SysRoleDO;
import com.fhs.basics.vo.SysRoleVO;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 角色服务
 * @Version: 1.0
 * @Author: jackwong
 * @Date 2020-03-19
 */
public interface SysRoleService extends BaseService<SysRoleVO, SysRoleDO>
{
    /**
     * 资源类型
     */
    String RESOURCETYPES = "resourceTypes";

    /**
     * 部门
     */
    String DEPTS = "depts";

    /**
     * 小区
     */
    String PROJECTS = "projects";


    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    public boolean addRole(SysRoleDO role);

    /**
     * 添加角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public boolean addButtons(SysRoleDO adminRole);

    /**
     * 删除角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public boolean deleteButtons(SysRoleDO adminRole);

    /**
     * 删除角色信息
     *
     * @param adminRole
     * @return
     */
    public boolean deleteRole(SysRoleDO adminRole);

    /**
     * 修改角色信息
     *
     * @param adminRole
     * @return
     */
    public boolean updateRole(SysRoleDO adminRole);

    /**
     * 查询角色的按钮信息列表
     *
     * @param adminRole
     * @return
     */
    public List<Map<String, Object>> searchButtons(SysRoleDO adminRole);

    /**
     * 根据角色查询按钮id
     *
     * @param map
     * @return
     */
    public List<String> searchButtonId(Map<String, Object> map);

    /**
     * 根据角色获取角色对象
     *
     * @param map
     * @return
     */
    public List<SysRoleVO> findRoleByGroupCode(Map<String, Object> map);

    /**
     *  获取一个用户的所有角色
     * @param userId 用户id
     * @return 角色集合
     */
    public List<SysRoleVO> findRolesByUserId(String userId);

    /**
     * 根据roleid查询用户关联表用户数
     * @param paramMap 查询条件
     * @return 关联用户数量
     */
    Integer findUserCountByRoleId(Map<String, Object> paramMap);
}
