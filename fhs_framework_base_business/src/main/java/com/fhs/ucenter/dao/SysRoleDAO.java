package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色DAO
 * @author jianbo.qin
 *
 */
@MapperDefinition(domainClass = SysRole.class)
@Repository
public interface SysRoleDAO extends BaseDao<SysRole>
{
    /**
     * 添加角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public int addButtons(SysRole adminRole);

    /**
     * 删除角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public int deleteButtons(SysRole adminRole);

    /**
     * 查询角色的按钮信息
     *
     * @param adminRole
     * @return
     */
    public List<Map<String, Object>> searchButtons(SysRole adminRole);

    public List<String> searchButtonId(Map<String, Object> map);

    /**
     * 根据角色获取角色对象
     *
     * @param map
     * @return
     */
    public List<SysRole> findRoleByGroupCode(Map<String, Object> map);

    /**
     * 通过用户ID获取角色
     *
     * @param userId 用户id
     * @return 用户角色集合
     */
    public List<SysRole> findRolesByUserId(@Param("userId") String userId);

    /**
     * 获取所有角色
     *
     * @parammap
     * @return
     */
    public List<SysRole> findForListAll();

    /**
     * 根据roleid查询用户关联表用户数
     * @param paramMap 查询条件
     * @return 关联用户数量
     */
    Integer findUserCountByRoleId(Map<String, Object> paramMap);

    /**
     * 删除角色用户关联
     * @param adminRole
     */
    void deleteUserRela(SysRole adminRole);
}
