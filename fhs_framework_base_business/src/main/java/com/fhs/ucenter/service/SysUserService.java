package com.fhs.ucenter.service;

import com.alibaba.fastjson.JSONArray;
import com.fhs.core.base.service.BaseService;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.bean.LeftMenu;
import com.fhs.ucenter.bean.SysMenu;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 用户service
 *
 * @author jianbo.qin
 *
 */
public interface SysUserService extends BaseService<SysUser>
{

    /**
     * 超管用户标识
     */
    Integer SYS_USER_IS_ADMIN = 1;

    public SysUser login(SysUser adminUser);

    /**
     * 发送邮件
     *
     * @param adminUser
     */
    public void sendMail(SysUser adminUser, String pas);

    /***
     * 获取密码
     *
     * @param userName
     * @return
     */
    public String readPass(String userName);

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
    public boolean deleteUserRole(SysUser adminUser);

    /**
     * 添加用户信息
     *
     * @param adminUser
     * @return
     */
    public Map<String, Object> addUser(SysUser adminUser);

    /**
     * 修改用户信息
     *
     * @param adminUser
     * @return
     */
    public boolean updateUser(SysUser adminUser);

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
     * 更加用户构建菜单数据
     *
     * @param adminUser
     * @return
     */
    public JSONArray buildMenuJson(SysUser adminUser);

    /**
     * 测试使用
     *
     * @param map
     * @return
     */
    public List<SysMenu> Test(Map<String, Object> map);

    /**
     * 根据用户查询权限
     *
     * @param adminUser
     * @return
     */
    public List<SysMenuPermission> searchUserButton(SysUser adminUser);

    /**
     * 监测原始密码是否正确
     *
     * @paramsysUserGroupcode
     * @return
     */
    public boolean validataPass(SysUser adminUser);

    /**
     * 修改用户密码
     *
     * @paramsysUserGroupcode
     * @return
     */
    public boolean updatePass(SysUser adminUser);

    /**
     * 根据用户名称获取权限
     *
     * @param adminUser
     * @return
     */
    public List<String> selectMenuByUname(SysUser adminUser);

    /**
     * 根据用户名称获取权限
     *
     * @param adminUser,menuState(0:物业的,1:社区的)
     * @return
     */
    public List<String> selectMenuByUname(SysUser adminUser, int menuState);

    /**
     * 通过登录名获取用户
     *
     * @param adminUser
     * @return
     */
    public SysUser selectUserByULname(SysUser adminUser);

    /**
     * 校验登录名是否存在
     *
     * @paramsysUserGroupcode
     * @return
     */
    boolean validataLoginName(SysUser adminUser);

    /**
     * 运营商登录验证
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> checkOperatorLogin(Map<String, Object> paramMap);

    /**
     * 根据名称获取用户信息
     */
    public SysUser findUserByName(String userName);

    /**
     * 根据用户名称查询所有权限
     */
    public List<String> findMenuButtonByName(String userName);

    /**
     * 刷新所有用户缓存
     * @return
     */
    HttpResult refreshRedisCache();

    /**
     * 获取当前用户的 左侧菜单
     * @param user 当前登录用户
     * @param menuType 菜单类型
     * @return 他有权限的左侧菜单
     */
    List<LeftMenu> getMenu(SysUser user, String menuType);

    /**
     * @desc 根据用户id获取用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    SysUser findSysUserById(String userId);

    /**
     * 删除用户
     */
    Boolean deleteSysUserById(String userId);

    /**
     * 根据条件查询用户数
     * @param paramMap 查询条件
     * @return 用户数
     */
    Integer findUserByOrgId(Map<String, Object> paramMap);

    /**
     * 获取用户权限URL
     * @param sysUser 后台用户
     * @return 用户权限URL列表
     */
    List<String>  getPermissionUrl(SysUser sysUser);

    /**
     * 根据用户id获取用户的数据权限
     * map - key -> 数据权限的类型，value是数据权限的id集合
     *    比如 parkIds->'1','2' 已经处理好了逗号，直接使用 IN 过滤 就可以
     * @param userId 用户id
     * @return 用户数据权限信息
     */
    Map<String,String> findUserDataPermissions(String userId);

}
