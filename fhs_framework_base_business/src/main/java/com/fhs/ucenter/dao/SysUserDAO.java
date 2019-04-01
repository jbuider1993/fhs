package com.fhs.ucenter.dao;

import com.mybatis.jpa.annotation.MapperDefinition;
import com.fhs.core.base.dao.BaseDao;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统用户DAO
 * @author jianbo.qin
 *
 */
@MapperDefinition(domainClass = SysUser.class)
@Repository
public interface SysUserDAO extends BaseDao<SysUser>
{
    /**
     * 用户登录
     * */
    public SysUser login(SysUser adminUser);

    /**
     * 添加用户角色
     *
     * @param adminUser
     * @return
     */
    public int addUserRole(SysUser adminUser);

    /**
     * 查询用户角色
     *
     * @param adminUser
     * @return
     */
    public List<Map<String, Object>> searchUserRole(SysUser adminUser);

    /**
     * 删除当前用户的角色
     *
     * @param adminUser
     * @return
     */
    public int deleteUserRole(SysUser adminUser);

    /**
     * 根据用户查询菜单
     *
     * @param adminUser
     * @return
     */
    public List<SysMenu> selectMenuByUid(SysUser adminUser);

    /**
     * 根据父节点查询菜单
     *
     * @param map
     * @return
     */
    public SysMenu selectParentMenuByid(Map<String, Object> map);

    /**
     * 根据Ids查询菜单
     *
     * @param map
     * @return
     */
    public List<SysMenu> readMenuByIds(Map<String, Object> map);

    /**
     * 监测原始密码是否正确
     *
     * @paramsysUserGroupcode
     * @return
     */
    public int validataPass(SysUser adminUser);

    /**
     * 根据登录名获取用户数
     *
     * @paramsysUserGroupcode
     * @return
     */
    public int getAdminUserCountByLoginName(SysUser adminUser);

    /**
     * 修改用户密码
     *
     * @paramsysUserGroupcode
     * @return
     */
    public int updatePass(SysUser adminUser);

    /**
     * 根据用户查询权限
     *
     * @param adminUser
     * @return
     */
    public List<SysMenuPermission> searchUserButton(SysUser adminUser);

    /**
     * 根据权限IDs获取权限对象
     *
     * @paramadminUser
     * @return
     */
    public List<SysMenuPermission> searchUserButtonIds(Map<String, Object> map);

    /**
     * 获取权限对象
     *
     * @paramadminUser
     * @return
     */
    public List<SysMenu> searchUserButtonAll(Map<String, Object> map);

    /**
     * 根据权限Ids查询菜单
     *
     * @param map
     * @return
     */
    public List<SysMenu> readMenuByButtonIds(Map<String, Object> map);

    /**
     * 根据用户权限ids获取菜单
     *
     * @param adminUser
     * @return
     */
    public List<SysMenu> selectMenuByButtonIds(Map<String, Object> adminUser);

    /**
     * 根据用户名称获取菜单
     *
     * @paramadminUser
     * @return
     */
    public List<SysMenu> selectMenuByUname(Map<String, Object> paramMap);

    /**
     * 通过登录名获取用户
     *
     * @param adminUser
     * @return
     */
    public SysUser selectUserByULname(SysUser adminUser);

    /**
     * 通过权限ids获取菜单
     *
     * @param map
     * @return
     */
    public List<SysMenu> selectMenuByButtons(Map<String, Object> map);

    /**
     * 获取所有菜单
     *
     * @return
     */
    public List<SysMenu> selectMenuAll(Map<String, Object> map);

    /**
     * 通过权限ids获取特定集合，main项目调用
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> searchButtons(Map<String, Object> map);

    /**
     * 根据传来的fatherIds查出所有的father信息
     *
     * @param fatherMenuIds
     * @return List<SysMenu>
     */
    public List<SysMenu> getFatherMenu(@Param("fatherMenuIds") String fatherMenuIds);

    /**
     * 获取所有不是平台账号登陆而且已经根据添加应用过滤之后的菜单
     *
     * @return
     */
    public List<SysMenu> selectNotPlanfromMenuAll(Map<String, Object> map);

    /**
     * 通过有权限的buttonid 查询所有的菜单id集合
     *
     * @param paramMap
     * @return
     */
    List<Integer> findAllHasPermissionId(Map<String, Object> paramMap);

    /**
     * 运营商登录验证
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> checkOperatorLogin(Map<String, Object> paramMap);

    /**
     * 根据admin有权限的菜单
     * @param user SysUser用户
     * @return 用户有权限的 菜单id
     */
    List<Integer> selectMenuIdByAdmin(SysUser user);

    /**
     * 根据userid获取user有权限的菜单
     * @param user 普通用户
     * @return 用户有权限的 菜单id
     */
    List<Integer> selectMenuIdByUserId(SysUser user);

    /**
     * 根据条件查询用户数
     * @param paramMap 查询条件
     * @return 用户数
     */
    Integer findUserByOrgId(Map<String, Object> paramMap);

    /**
     * 根据用户ID获取用户权限URL
     * @param userId 用户ID
     * @return 用户权限URL列表
     */
    List<String> getPermissionUrlByUserId(@Param("userId") String userId);

    /**
     * 查询所有权限URL
     * @return 用户权限URL列表
     */
    List<String> getPermissionUrlAll();
}
