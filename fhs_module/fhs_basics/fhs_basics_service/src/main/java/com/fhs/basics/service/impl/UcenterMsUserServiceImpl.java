package com.fhs.basics.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.basics.api.rpc.FeignSysUserApiService;
import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.constant.BasicsMenuConstant;
import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.basics.dox.UcenterMsTenantDO;
import com.fhs.basics.form.SysUserForm;
import com.fhs.basics.mapper.SettMsMenuMapper;
import com.fhs.basics.mapper.UcenterMsUserMapper;
import com.fhs.basics.service.SettMsMenuService;
import com.fhs.basics.service.UcenterMsRoleService;
import com.fhs.basics.service.UcenterMsUserService;
import com.fhs.basics.service.UcenterMsTenantService;
import com.fhs.basics.vo.*;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.core.valid.checker.ParamChecker;
import com.google.common.collect.HashMultimap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Primary
@Service("ucenterMsUserService")
@DataSource("base_business")
@AutoTrans(namespace = BaseTransConstant.USER_INFO, fields = "userName")
public class UcenterMsUserServiceImpl extends BaseServiceImpl<UcenterMsUserVO, UcenterMsUserDO> implements UcenterMsUserService, FeignSysUserApiService {

    private final int ADMIN = 1;

    /**
     * 显示的菜单
     */
    private static final int SHOW = 0;


    @Value("${fhs.basics.passsalt:fhs}")
    private String passsalt;

    /**
     * redis 缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;

    @Autowired
    private UcenterMsUserMapper sysUserMapper;

    @Autowired
    private SettMsMenuMapper sysMenuMapper;

    @Autowired
    private UcenterMsRoleService roleService;


    @Lazy
    @Autowired
    private UcenterMsTenantService tenantService;


    @Override
    public UcenterMsUserVO login(UcenterMsUserDO adminUser) {
        return d2v(sysUserMapper.login(adminUser));
    }

    @Override
    public void sendMail(UcenterMsUserDO adminUser, String pas) {
        //如果开通要发邮件的话可以写到这里

    }

    @Override
    public String readPass(String userName) {
        return ENCodeUtils.encodeByMD5(userName + passsalt).toLowerCase();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addUserRole(UcenterMsUserDO adminUser) {
        return sysUserMapper.addUserRole(adminUser);
    }

    @Override
    public List<Map<String, Object>> searchUserRole(UcenterMsUserDO adminUser) {
        return sysUserMapper.searchUserRole(adminUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteUserRole(UcenterMsUserDO adminUser) {
        try {
            sysUserMapper.deleteUserRole(adminUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> addUser(UcenterMsUserDO adminUser) {
        int count = 0;
        if (StringUtil.isEmpty(adminUser.getUserId())) { //新增
            adminUser.setUserId(StringUtil.getUUID());
            count = this.insertSelective(adminUser);
        } else {//修改
            count = super.updateSelectiveById(adminUser);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (count > 0) {
            // 添加用户成功时插入当前用户角色
            if (adminUser.getRoleList() != null && adminUser.getRoleList().length > 0) {
                //删除用户角色中间表数据
                deleteUserRole(adminUser);
                //新增用户角色中间表
                addUserRole(adminUser);
            }
            paramMap.put("adminUser", adminUser);
            paramMap.put("retult", true);
            return paramMap;
        }
        paramMap.put("retult", false);
        return paramMap;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateUser(UcenterMsUserDO adminUser) {
        // 删除原有的角色
        boolean count = deleteUserRole(adminUser);
        if (count) {
            // 修改用户信息
            super.updateSelectiveById(adminUser);
            if (adminUser.getRoleList().length > 0) {
                // 插入新的用户角色
                int count1 = addUserRole(adminUser);
                return count1 > 0;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户查询菜单
     */
    @Override
    public List<SettMsMenuVO> selectMenuByUid(UcenterMsUserDO adminUser) {
        return ListUtils.copyListToList(sysUserMapper.selectMenuByUid(adminUser), SettMsMenuVO.class);
    }

    /**
     * 构建菜单树数据
     *
     * @param adminUser
     * @return
     */
    @Override
    public JSONArray buildMenuJson(UcenterMsUserDO adminUser) {
        List<SettMsMenuVO> menuList = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuType", "0");//运营菜单
        UcenterMsUserDO temp = super.findBean(adminUser);
        if (temp.getIsAdmin() == ADMIN) {
            menuList = ListUtils.copyListToList(sysUserMapper.selectMenuAll(map), SettMsMenuVO.class);
        } else {
            menuList = selectMenuByUid(adminUser);
        }
        JSONArray array = searchArrayGroupby(menuList);
        return array;
    }

    private List<SettMsMenuVO> dropDoulbe(List<SettMsMenuVO> menuList) {
        MyMap<String, SettMsMenuVO> map = new MyMap<String, SettMsMenuVO>();
        for (SettMsMenuVO menu : menuList) {
            if (menu.getMenuState() != SHOW) {
                continue;
            }
            map.put(menu.getFatherMenuId() + "" + menu.getMenuId(), menu);
        }
        return map.getValueList();
    }


    @Override
    public UcenterMsUserVO findBean(UcenterMsUserDO bean) {
        UcenterMsUserVO adminUser = super.findBean(bean);
        return adminUser;
    }

    @Override
    public List<UcenterMsUserVO> findForListFromMap(Map<String, Object> map) {
        List<UcenterMsUserVO> result = super.findForListFromMap(map);
        return result;
    }

    /**
     * 根据父Id分组构建json数据
     *
     * @param menuList
     * @return
     */

    public JSONArray searchArrayGroupby(List<SettMsMenuVO> menuList) {
        menuList = dropDoulbe(menuList);
        MyMap<String, JSONObject> tempmap = new MyMap<String, JSONObject>();
        MyMap<String, JSONObject> mapobj = new MyMap<String, JSONObject>();
        MyMap<String, Integer> map = caseListToMap(menuList);
        for (Integer item : map.getValueList()) {
            JSONArray temparr = new JSONArray();
            List<SettMsMenuVO> templist = seachChildJson(item, menuList);
            for (SettMsMenuVO menu : templist) {
                JSONObject menujson = new JSONObject();
                menujson.put("id", menu.getMenuId());
                menujson.put("name", menu.getMenuName());
                menujson.put("url", menu.getMenuUrl());
                menujson.put("img", "");
                menujson.put("serverUrl", menu.getServerUrl());
                menujson.put("sonMenu", new JSONArray());
                temparr.add(menujson);
                tempmap.put(menu.getMenuId().toString(), menujson);
            }
            JSONObject tempobj = searchMenuByParentId(item, temparr, tempmap);
            if (tempobj == null) {
                continue;
            }
            String key = tempobj.getString("id") + "root";
            JSONObject res = tempmap.get(key);
            if (res == null) {
                tempmap.put(key, tempobj);
            }
            mapobj.put(key, tempobj);
        }
        JSONArray array = mapToJSONArray(mapobj);
        return array;
    }

    private JSONArray mapToJSONArray(MyMap<String, JSONObject> map) {
        JSONArray array = new JSONArray();
        List<String> set = map.getKeyList();
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            JSONObject tempob = map.get(iter.next());
            int id = tempob.getIntValue("id");
            if (id == 0) {
                array.addAll(tempob.getJSONArray("sonMenu"));
            } else {
                array.add(tempob);
            }
        }
        return array;
    }

    /**
     * 将菜单集合转换为父Id map表
     *
     * @param menuList
     * @return
     */
    private MyMap<String, Integer> caseListToMap(List<SettMsMenuVO> menuList) {
        MyMap<String, Integer> map = new MyMap<String, Integer>();
        for (SettMsMenuVO item : menuList) {
            map.put(ConverterUtils.toString(item.getFatherMenuId()), item.getFatherMenuId());
        }
        return map;
    }

    /**
     * 获取特定父Id的子项集合
     *
     * @param parentId
     * @param menuList
     * @return
     */
    private List<SettMsMenuVO> seachChildJson(Integer parentId, List<SettMsMenuVO> menuList) {
        List<SettMsMenuVO> array = new ArrayList<SettMsMenuVO>();
        for (SettMsMenuVO item : menuList) {
            if (parentId != null && (item.getFatherMenuId()) != null
                    && parentId.intValue() == item.getFatherMenuId().intValue()) {
                array.add(item);
            }
        }
        return array;
    }

    /**
     * 递归构建json数据
     *
     * @param parentId
     * @param child
     * @return
     */
    private JSONObject searchMenuByParentId(Integer parentId, JSONArray child, MyMap<String, JSONObject> parenmap) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fatherMenuId", parentId);
        SettMsMenuVO parentmenu = selectParentMenuById(map);
        if (parentmenu != null && parentmenu.getMenuId() != 0) {
            JSONObject menujson = parenmap.get(parentId.toString());
            JSONArray array = new JSONArray();
            if (menujson == null) {
                menujson = new JSONObject();
                menujson.put("id", parentmenu.getMenuId());
                menujson.put("name", parentmenu.getMenuName());
                menujson.put("url", parentmenu.getMenuUrl());
                menujson.put("img", "");
                menujson.put("sonMenu", child);

            } else {
                JSONArray temp = menujson.getJSONArray("sonMenu");
                temp.addAll(child);
            }
            array.add(menujson);
            parenmap.put(parentmenu.getMenuId().toString(), menujson);
            if (parentmenu.getFatherMenuId() != 0) {
                return searchMenuByParentId(parentmenu.getFatherMenuId(), array, parenmap);
            }
            return menujson;
        } else {
            return child.getJSONObject(0);
            // return null;
        }
    }

    /**
     * 获取根据子菜单获取父菜单
     */
    @Override
    public SettMsMenuVO selectParentMenuById(Map<String, Object> map) {
        SettMsMenuVO result = new SettMsMenuVO();
        BeanUtils.copyProperties(sysUserMapper.selectParentMenuById(map), result);
        return result;
    }

    /**
     * 校验密码
     */
    @Override
    public boolean validataPass(UcenterMsUserDO adminUser) {
        if (adminUser.getOldPassword() == null) {
            return false;
        }
        adminUser.setPassword(ENCodeUtils.encodeByMD5(adminUser.getOldPassword()).toLowerCase());
        int count = sysUserMapper.validataPass(adminUser);
        return count > 0;
    }

    /**
     * 验证登录名是否存在
     */
    @Override
    public boolean validataLoginName(UcenterMsUserDO adminUser) {
        int count = sysUserMapper.getAdminUserCountByLoginName(adminUser);
        return count <= 0;
    }

    /**
     * 更新密码
     */
    @Override
    public boolean updatePass(UcenterMsUserDO adminUser) {
        if (adminUser.getOldPassword() == null) {
            return false;
        }
        int count = sysUserMapper.validataPass(adminUser);
        if (count > 0) {
            if (adminUser.getNewPassword() == null) {
                return false;
            }
            adminUser.setPassword(adminUser.getNewPassword());
            count = sysUserMapper.updatePass(adminUser);
            if (count > 0) {
                // 发送邮件
                sendMail(adminUser, adminUser.getNewPassword());
            }
            return count > 0;
        } else {
            return false;
        }
    }

    @Override
    public List<SettMsMenuVO> Test(Map<String, Object> map) {
        return ListUtils.copyListToList(sysUserMapper.readMenuByIds(map), SettMsMenuVO.class);
    }

    /**
     * 获取用户操作权限
     */
    @Override
    public List<SettMsMenuPermissionVO> searchUserButton(UcenterMsUserDO adminUser) {
        return ListUtils.copyListToList(sysUserMapper.searchUserButton(adminUser), SettMsMenuPermissionVO.class);
    }

    /**
     * 根据用户获取菜单，shiro授权使用
     */
    @Override
    public List<String> selectMenuByUname(UcenterMsUserDO adminUser) {
        return selectMenuByUname(adminUser, SHOW);
    }

    @Override
    public List<String> selectMenuByUname(UcenterMsUserDO adminUser, int menuState) {
        List<SettMsMenuVO> adminMenus = null;
        UcenterMsUserVO tempUser = selectUserByULname(adminUser);
        if (tempUser == null) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (tempUser.getIsAdmin() == ADMIN)// 管理员时，全查
        {
            paramMap.put("menuState", menuState);
            adminMenus = ListUtils.copyListToList(sysMenuMapper.findForAllList(paramMap), SettMsMenuVO.class);
        } else {
            paramMap = MapUtils.bean2Map(adminUser);
            paramMap.put("menuState", menuState);
            adminMenus = ListUtils.copyListToList(sysUserMapper.selectMenuByUname(paramMap), SettMsMenuVO.class);
        }
        List<String> resulstList = readButtonsByList(adminMenus);
        return resulstList;
    }

    private List<String> readButtonsByList(List<SettMsMenuVO> adminMenus) {
        List<String> resulstList = new ArrayList<String>();
        for (SettMsMenuVO item : adminMenus) {
            resulstList.add(item.getNamespace());
        }
        return resulstList;
    }

    @Override
    public boolean delete(UcenterMsUserDO bean) {
        deleteUserRole(bean);
        return super.delete(bean);
    }

    @Override
    public UcenterMsUserVO selectUserByULname(UcenterMsUserDO adminUser) {
        return d2v(sysUserMapper.selectUserByULname(adminUser));
    }

    @Override
    public Map<String, Object> checkOperatorLogin(Map<String, Object> paramMap) {
        paramMap.put("password", ENCodeUtils.encodeByMD5(ConverterUtils.toString(paramMap.get("password"))).toLowerCase());
        return sysUserMapper.checkOperatorLogin(paramMap);
    }

    @Override
    public UcenterMsUserVO findUserByName(String userName) {
        UcenterMsUserVO adminUser = new UcenterMsUserVO();
        adminUser.setUserLoginName(userName);
        return selectUserByULname(adminUser);
    }

    @Override
    public List<String> findMenuButtonByName(String userName) {
        UcenterMsUserVO adminUser = new UcenterMsUserVO();
        adminUser.setUserLoginName(userName);
        return selectMenuByUname(adminUser);
    }

    @Override
    public List<LeftMenuVO> getMenu(UcenterMsUserDO user, String menuType) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //如果是saas模式需要判断菜单类型
        if (ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel"))) {
            //如果不是运营者的集团编码，只能是租户，如果是运营者的编码可以按照参数请求的来
            if (EConfig.getOtherConfigPropertiesValue("operator_group_code").equals(user.getGroupCode())) {
                paramMap.put("menuType", menuType);
            } else {
                paramMap.put("menuType", SettMsMenuService.MENU_TYPE_TENANT);
                if (ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel"))) {
                    String systemIds = tenantService.selectBean(UcenterMsTenantDO.builder().groupCode(user.getGroupCode()).build()).getSystemIds();
                    if (systemIds != null) {
                        paramMap.put("systemIds", StringUtil.getStrToIn(systemIds.split(",")));
                    }
                }
            }
        }
        List<SettMsMenuVO> menuList = ListUtils.copyListToList(sysUserMapper.selectMenuAll(paramMap), SettMsMenuVO.class);
        menuList = menuFilter(user, menuList);
        Map<Integer, LeftMenuVO> leftMenuMap = new HashMap<>();
        // 遍历AdminMenu转换为LeftMenu
        menuList.forEach(adminMenu -> {
            LeftMenuVO leftMenu = new LeftMenuVO()
                    .mk("id", adminMenu.getMenuId(), "serverUrl", adminMenu.getServerUrl(), "name", adminMenu.getMenuName(), "url", adminMenu.getMenuUrl(), "menuServer", adminMenu.getServerNameId(), "image", adminMenu.getImage());
            leftMenuMap.put(leftMenu.getId(), leftMenu);
        });
        List<LeftMenuVO> result = new ArrayList<>();
        menuList.forEach(adminMenu -> {
            if (ConverterUtils.toInt(adminMenu.getMenuState()) != SettMsMenuService.NOT_SHOW) {
                // 如果不是null 也不是root则找爸爸吧自己添加到爸爸的儿子里面去
                if (adminMenu.getFatherMenuId() != null && adminMenu.getFatherMenuId() != BasicsMenuConstant.MENU_ROOT) {
                    if (leftMenuMap.containsKey(adminMenu.getFatherMenuId())) {
                        leftMenuMap.get(adminMenu.getFatherMenuId()).getSonMenu().add(
                                leftMenuMap.get(adminMenu.getMenuId()));
                    }
                }
                // 如果是一级菜单则挂写到result去
                else if (adminMenu.getFatherMenuId() != null && adminMenu.getFatherMenuId() == BasicsMenuConstant.MENU_ROOT) {
                    result.add(leftMenuMap.get(adminMenu.getMenuId()));
                }
            }
        });
        return result;
    }


    /**
     * 从所有的菜单找到用户拥有权限的
     *
     * @param user
     * @param menuList
     * @return
     */
    private List<SettMsMenuVO> menuFilter(UcenterMsUserDO user, List<SettMsMenuVO> menuList) {
        Set<Integer> userMenuIds = null;
        if (user.getIsAdmin() == ADMIN) {
            userMenuIds = sysUserMapper.selectMenuIdByAdmin(user);
        } else {
            userMenuIds = sysUserMapper.selectMenuIdByUserId(user);
        }

        Map<Integer, SettMsMenuVO> menuMap = new HashMap<>();
        menuList.forEach(menu -> {
            menuMap.put(menu.getMenuId(), menu);
        });
        // 已经添加进结果的菜单
        Set<SettMsMenuVO> hasAddMenu = new HashSet<>();
        for (Integer userMenuId : userMenuIds) {
            if (menuMap.containsKey(userMenuId)) {
                hasAddMenu.add(menuMap.get(userMenuId));
                // 能看到儿子就能看到爸爸，找儿子的爸爸
                initFather(hasAddMenu, menuMap, menuMap.get(userMenuId));
            }
        }
        List<SettMsMenuVO> result = new ArrayList<>();
        result.addAll(hasAddMenu);
        result.sort(new Comparator<SettMsMenuVO>() {
            @Override
            public int compare(SettMsMenuVO o1, SettMsMenuVO o2) {
                if (o1.getFatherMenuId() == null) {
                    return -1;
                }
                if (o2.getFatherMenuId() == null) {
                    return 1;
                }
                if (o1.getFatherMenuId() - o2.getFatherMenuId() == 0) {
                    return ConverterUtils.toInt(o1.getOrderIndex()) - ConverterUtils.toInt(o2.getOrderIndex());
                }
                return o1.getFatherMenuId() - o2.getFatherMenuId();
            }
        });
        return result;
    }

    /**
     * 找一个菜单的爸爸，保证拥有子菜单 可以显示出父菜单
     *
     * @param hasAddMenu
     * @param menuMap
     * @param sysMenu
     */
    private void initFather(Set<SettMsMenuVO> hasAddMenu, Map<Integer, SettMsMenuVO> menuMap, SettMsMenuVO sysMenu) {
        if (menuMap.containsKey(sysMenu.getFatherMenuId())) {
            SettMsMenuVO father = menuMap.get(sysMenu.getFatherMenuId());
            if (!hasAddMenu.contains(father)) {
                hasAddMenu.add(father);
                // 很愉快的找打爸爸后接着网上找 找father的爸爸
                initFather(hasAddMenu, menuMap, father);
            }
        }
    }

    /**
     * @param userId 用户id
     * @return 用户信息
     * @desc 根据用户id获取用户信息
     */
    @Override
    public UcenterMsUserVO selectById(Object userId) {
        //根据id获取用户信息
        UcenterMsUserVO sysUser = super.selectById(userId);
        //根据用户id获取当前用户的角色
        List<Map<String, Object>> sysUserRoleList = this.searchUserRole(sysUser);
        if (sysUserRoleList.size() > 0) {
            Vector<String> roleVectorList = new Vector<>();
            for (int i = 0; i < sysUserRoleList.size(); i++) {
                Map<String, Object> map = sysUserRoleList.get(i);
                roleVectorList.add(map.get("roleId").toString());
            }
            String[] roleList = new String[roleVectorList.size()];
            roleVectorList.toArray(roleList);
            sysUser.setRoleList(roleList);
            sysUser.setRoleIds(StringUtil.getStrForIn(roleVectorList, false));
        } else {
            sysUser.setRoleList(new String[0]);
        }
        return sysUser;
    }

    /**
     * @param userId 要删除的用户de id
     * @return 删除是否成功
     * @desc 根据id删除用户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Boolean deleteSysUserById(String userId) {
        this.deleteById(userId);
        UcenterMsUserDO sysUser = new UcenterMsUserDO();
        sysUser.setUserId(userId);
        return this.deleteUserRole(sysUser);
    }

    @Override
    public Integer findUserByOrgId(Map<String, Object> paramMap) {
        return sysUserMapper.findUserByOrgId(paramMap);
    }

    @Override
    public List<String> getPermissionUrl(UcenterMsUserDO sysUser) {
        // 如果是admin则返回所有的url
        if (sysUser.getIsAdmin() == UcenterMsUserService.SYS_USER_IS_ADMIN) {
            return getPermissionUrlAll();
        }
        return getPermissionUrlByUserId(sysUser.getUserId());

    }

    @Override
    public Map<String, String> findUserDataPermissions(String userId) {
        List<UcenterMsRoleVO> roleList = roleService.findRolesByUserId(userId);
        Map<String, String> resultMap = new HashMap<>();
        //谷歌的map value是一个hashset
        final HashMultimap<String, String> dataPermissionTempMap = HashMultimap.create();
        //有多个角色，把多个角色的数据权限合并
        roleList.forEach(role -> {
            if (CheckUtils.isNotEmpty(role.getDataPermissions())) {
                final JSONObject oneRoleDataPermisstionMap = JSON.parseObject(role.getDataPermissions());
                oneRoleDataPermisstionMap.keySet().forEach(key -> {
                    String tempPermissions = oneRoleDataPermisstionMap.getString(key);
                    if (CheckUtils.isNotEmpty(tempPermissions)) {
                        dataPermissionTempMap.putAll(key, ListUtils.array2List(tempPermissions.split(",")));
                    }
                });
            }
        });
        dataPermissionTempMap.keySet().forEach(key -> {
            Set<String> dataPermissionsSet = dataPermissionTempMap.get(key);
            resultMap.put(key, StringUtil.getStrForIn(dataPermissionsSet, true));
        });
        UcenterMsUserVO user = this.selectById(userId);
        // 如果不是不是管理员，哪些数据权限他没有设置为-1
        if (user.getIsAdmin() != Constant.INT_TRUE) {
            String[] permissonDataKeys = ConverterUtils.toString(EConfig.getOtherConfigPropertiesValue("permissonDataKey")).split(",");
            for (String permissonDataKey : permissonDataKeys) {
                if (!resultMap.containsKey(permissonDataKey)) {
                    resultMap.put(permissonDataKey, "-1");
                }
            }
        }
        return resultMap;
    }

    @Override
    public List<SysUserOrgVO> getUserOrgTreeList(String groupCode) {
        List<SysUserOrgVO> dbRecord = sysUserMapper.getUserOrgTreeList(groupCode);

        //找不到爸爸的才会放到此里面
        List<SysUserOrgVO> result = new ArrayList<>();

        Map<String, SysUserOrgVO> fatherDTO = new HashMap<>();
        for (SysUserOrgVO user : dbRecord) {
            fatherDTO.put(user.getId(), user);
            if (fatherDTO.containsKey(user.getParentId())) {
                fatherDTO.get(user.getParentId()).getChildren().add(user);
            } else {
                result.add(user);
            }
        }
        return result;
    }


    @Override
    public HttpResult<UcenterMsUserVO> getSysUserByName(String userLoginName) {
        return HttpResult.success(this.findBean(new UcenterMsUserVO().mk("userLoginName", userLoginName)));
    }

    @Override
    public HttpResult<List<String>> selectMenuByUname(String userLoginName) {
        List<String> list = this.selectMenuByUname(this.findBean(new UcenterMsUserVO().mk("userLoginName", userLoginName)));
        return HttpResult.success(list);
    }

    @Override
    public HttpResult<Pager<UcenterMsUserVO>> getSysUserList(SysUserForm sysUserForm) {
        UcenterMsUserVO sysUser = new UcenterMsUserVO();
        BeanUtils.copyProperties(sysUserForm, sysUser);
        List<UcenterMsUserVO> sysUsersList = this.findForList(sysUser, sysUserForm.getPageStart() - 1, sysUserForm.getPageSize());
        if (sysUsersList.size() > 0) {
            List<UcenterMsUserVO> sysUserVoList = new ArrayList<>();
            sysUsersList.forEach(sysUserForEach -> {
                //正则表达式，替换手机号中间4位
                sysUserForEach.setMobile(sysUserForEach.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                sysUserVoList.add(sysUserForEach);
            });
            int count = this.findCount(sysUser);
            return count > 0 ? HttpResult.success(new Pager<>(count, sysUserVoList)) : HttpResult.success(new Pager<UcenterMsUserVO>(0, null));
        } else {
            return HttpResult.success(new Pager<UcenterMsUserVO>(0, null));
        }
    }

    @Override
    public HttpResult<List<String>> getPermissionUrlByUserIdFeign(String userId) {
        UcenterMsUserVO sysUser = this.selectById(userId);
        if (sysUser == null) {
            return HttpResult.error(null, "没有此用户");
        } else {
            return HttpResult.success(this.getPermissionUrl(sysUser));
        }
    }

    private List<String> getPermissionUrlByUserId(String userId) {
        return sysUserMapper.getPermissionUrlByUserId(userId);
    }

    @Override
    public HttpResult<Map<String, String>> getDataUserPermisstion(String userId) {
        ParamChecker.isNotNullOrEmpty(userId, "userId不能为空");
        return HttpResult.success(this.findUserDataPermissions(userId));
    }

    @Override
    public HttpResult<UcenterMsUserVO> getSysUserByUserId(SysUserForm sysUserForm) {
        UcenterMsUserVO vo = new UcenterMsUserVO();
        if (!CheckUtils.isNullOrEmpty(sysUserForm) && !CheckUtils.isNullOrEmpty(sysUserForm.getUserId())) {
            UcenterMsUserVO sysUser = this.selectById(sysUserForm.getUserId());
            return HttpResult.success(sysUser);
        }
        throw new ParamException("userid不可为空");
    }

    @Override
    public HttpResult<List<UcenterMsUserVO>> getSysUserByOrganizationId(String organizationId) {
        ParamChecker.isNotNullOrEmpty(organizationId, "userId不能为空");
        List<UcenterMsUserVO> sysUserList = this.findForList(UcenterMsUserVO.builder().organizationId(organizationId).build());
        return HttpResult.success(sysUserList);
    }

    private List<String> getPermissionUrlAll() {
        return sysUserMapper.getPermissionUrlAll();
    }

}
