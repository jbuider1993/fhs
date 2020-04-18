package com.fhs.basics.service;

import com.alibaba.fastjson.JSONArray;
import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.basics.vo.*;
import com.fhs.core.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 用户service
 *
 * @author jianbo.qin
 */
public interface UcenterMsUserService extends BaseService<UcenterMsUserVO, UcenterMsUserDO> {

    /**
     * 超管用户标识
     */
    Integer SYS_USER_IS_ADMIN = 1;

    public UcenterMsUserVO login(UcenterMsUserDO adminUser);

    /**
     * 发送邮件
     *
     * @param adminUser
     */
    public void sendMail(UcenterMsUserDO adminUser, String pas);

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
    public int addUserRole(UcenterMsUserDO adminUser);

    /**
     * 查询用户角色
     *
     * @param adminUser
     * @return
     */
    public List<Map<String, Object>> searchUserRole(UcenterMsUserDO adminUser);

    /**
     * 删除当前用户的角色
     *
     * @param adminUser
     * @return
     */
    public boolean deleteUserRole(UcenterMsUserDO adminUser);

    /**
     * 添加用户信息
     *
     * @param adminUser
     * @return
     */
    public Map<String, Object> addUser(UcenterMsUserDO adminUser);

    /**
     * 修改用户信息
     *
     * @param adminUser
     * @return
     */
    public boolean updateUser(UcenterMsUserDO adminUser);

    /**
     * 根据用户查询菜单
     *
     * @param adminUser
     * @return
     */
    public List<SettMsMenuVO> selectMenuByUid(UcenterMsUserDO adminUser);

    /**
     * 根据父节点查询菜单
     *
     * @param map
     * @return
     */
    public SettMsMenuVO selectParentMenuById(Map<String, Object> map);

    /**
     * 更加用户构建菜单数据
     *
     * @param adminUser
     * @return
     */
    public JSONArray buildMenuJson(UcenterMsUserDO adminUser);



    /**
     * 根据用户查询权限
     *
     * @param adminUser
     * @return
     */
    public List<SettMsMenuPermissionVO> searchUserButton(UcenterMsUserDO adminUser);

    /**
     * 监测原始密码是否正确
     *
     * @return
     * @paramsysUserGroupcode
     */
    public boolean validataPass(UcenterMsUserDO adminUser);

    /**
     * 修改用户密码
     *
     * @return
     * @paramsysUserGroupcode
     */
    public boolean updatePass(UcenterMsUserDO adminUser);

    /**
     * 根据用户名称获取权限
     *
     * @param adminUser
     * @return
     */
    public List<String> selectMenuByUname(UcenterMsUserDO adminUser);

    /**
     * 根据用户名称获取权限
     *
     * @param adminUser,menuState(0:物业的,1:社区的)
     * @return
     */
    public List<String> selectMenuByUname(UcenterMsUserDO adminUser, int menuState);

    /**
     * 通过登录名获取用户
     *
     * @param adminUser
     * @return
     */
    UcenterMsUserVO selectUserByULname(UcenterMsUserDO adminUser);

    /**
     * 校验登录名是否存在
     *
     * @return
     * @paramsysUserGroupcode
     */
    boolean validataLoginName(UcenterMsUserDO adminUser);

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
    public UcenterMsUserVO findUserByName(String userName);

    /**
     * 根据用户名称查询所有权限
     */
    public List<String> findMenuButtonByName(String userName);


    /**
     * 获取当前用户的 左侧菜单
     *
     * @param user     当前登录用户
     * @param menuType 菜单类型
     * @return 他有权限的左侧菜单
     */
    List<LeftMenuVO> getMenu(UcenterMsUserDO user, String menuType);

    /**
     * 获取vue用的菜单
     * @param user  用户
     * @param menuType 菜单类型
     * @return vue路由菜单
     */
    List<VueRouterVO> getRouters(UcenterMsUserDO user, String menuType);


    /**
     * 删除用户
     */
    Boolean deleteSysUserById(String userId);

    /**
     * 根据条件查询用户数
     *
     * @param paramMap 查询条件
     * @return 用户数
     */
    Integer findUserByOrgId(Map<String, Object> paramMap);

    /**
     * 获取用户权限URL
     *
     * @param sysUser 后台用户
     * @return 用户权限URL列表
     */
    List<String> getPermissionUrl(UcenterMsUserDO sysUser);

    /**
     * 根据用户id获取用户的数据权限
     * map - key -> 数据权限的类型，value是数据权限的id集合
     * 比如 parkIds->'1','2' 已经处理好了逗号，直接使用 IN 过滤 就可以
     *
     * @param userId 用户id
     * @return 用户数据权限信息
     */
    Map<String, String> findUserDataPermissions(String userId);

    /**
     * 根据集团编码获取集团下所有的用户tree
     *
     * @param groupCode 集团编码
     * @return 集团下所有的用户tree
     */
    List<SysUserOrgVO> getUserOrgTreeList(String groupCode);

}
