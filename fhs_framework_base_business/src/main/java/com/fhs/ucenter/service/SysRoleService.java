package com.fhs.ucenter.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends BaseService<SysRole>
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
    public boolean addRole(SysRole role);

    /**
     * 添加角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public boolean addButtons(SysRole adminRole);

    /**
     * 删除角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public boolean deleteButtons(SysRole adminRole);

    /**
     * 删除角色信息
     *
     * @param adminRole
     * @return
     */
    public boolean deleteRole(SysRole adminRole);

    /**
     * 修改角色信息
     *
     * @param adminRole
     * @return
     */
    public boolean updateRole(SysRole adminRole);

    /**
     * 查询角色的按钮信息列表
     *
     * @param adminRole
     * @return
     */
    public List<Map<String, Object>> searchButtons(SysRole adminRole);

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
    public List<SysRole> findRoleByGroupCode(Map<String, Object> map);

    /**
     *  获取一个用户的所有角色
     * @param userId 用户id
     * @return 角色集合
     */
    public List<SysRole> findRolesByUserId(String userId);

    /**
     * 根据roleid查询用户关联表用户数
     * @param paramMap 查询条件
     * @return 关联用户数量
     */
    Integer findUserCountByRoleId(Map<String, Object> paramMap);
}
