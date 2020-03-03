package com.fhs.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.core.strategy.GenInfo;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.ucenter.bean.*;
import com.fhs.ucenter.dao.SysMenuDAO;
import com.fhs.ucenter.dao.SysUserDAO;
import com.fhs.ucenter.dto.SysUserOrgDTO;
import com.fhs.ucenter.service.SysMenuService;
import com.fhs.ucenter.service.SysRoleService;
import com.fhs.ucenter.service.SysUserService;
import com.fhs.ucenter.service.UcenterMsTenantService;
import com.google.common.collect.HashMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("sysUserServiceImpl")
@DataSource("base_business")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    private final int ADMIN = 1;

    /**
     * 显示的菜单
     */
    private static final int SHOW = 0;

    /**
     * ROOT
     */
    private static final int ROOT = 0;

    /**
     * redis 缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;

    @Autowired
    private SysUserDAO sysUserDAO;

    @Autowired
    private SysMenuDAO sysMenuDAO;

    @Autowired
    private SysRoleService roleService;



    @Lazy
    @Autowired
    private UcenterMsTenantService tenantService;

    @Override
    public SysUser login(SysUser adminUser) {
        return sysUserDAO.login(adminUser);
    }

    @Override
    public void sendMail(SysUser adminUser, String pas) {
        //如果开通要发邮件的话可以写到这里

    }

    @Override
    public String readPass(String userName) {
        return ENCodeUtils.encodeByMD5(userName + "fhs_framework").toLowerCase();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addUserRole(SysUser adminUser) {
        return sysUserDAO.addUserRole(adminUser);
    }

    @Override
    public List<Map<String, Object>> searchUserRole(SysUser adminUser) {
        return sysUserDAO.searchUserRole(adminUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteUserRole(SysUser adminUser) {
        try {
            sysUserDAO.deleteUserRole(adminUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    @GenInfo
    public Map<String, Object> addUser(SysUser adminUser) {
        int count = 0;
        if (StringUtil.isEmpty(adminUser.getUserId())) { //新增
            adminUser.setUserId(StringUtil.getUUID());
            count = this.insertJpa(adminUser);
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
    public boolean updateUser(SysUser adminUser) {
        // 删除原有的角色
        boolean count = deleteUserRole(adminUser);
        if (count) {
            // 修改用户信息
            boolean bean = super.updateSelectiveById(adminUser)>0;
            if (bean) {
                if (adminUser.getRoleList().length > 0) {
                    // 插入新的用户角色
                    int count1 = addUserRole(adminUser);
                    return count1 > 0;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据用户查询菜单
     */
    @Override
    public List<SysMenu> selectMenuByUid(SysUser adminUser) {
        return sysUserDAO.selectMenuByUid(adminUser);
    }

    /**
     * 构建菜单树数据
     *
     * @param adminUser
     * @return
     */
    @Override
    public JSONArray buildMenuJson(SysUser adminUser) {
        List<SysMenu> menuList = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuType", "0");//运营菜单
        SysUser temp = sysUserDAO.findBean(adminUser);
        if (temp.getIsAdmin() == ADMIN) {
            menuList = sysUserDAO.selectMenuAll(map);
        } else {
            menuList = selectMenuByUid(adminUser);
        }
        JSONArray array = searchArrayGroupby(menuList);
        return array;
    }

    private List<SysMenu> dropDoulbe(List<SysMenu> menuList) {
        MyMap<String, SysMenu> map = new MyMap<String, SysMenu>();
        for (SysMenu menu : menuList) {
            if (menu.getMenuState() != SHOW) {
                continue;
            }
            map.put(menu.getFatherMenuId() + "" + menu.getMenuId(), menu);
        }
        return map.getValueList();
    }


    @Override
    public SysUser findBean(SysUser bean) {
        SysUser adminUser = super.findBean(bean);
//    	super.transBeanSingleField(adminUser, "cityCode", "cityName", "6100000");
        return adminUser;
    }

    @Override
    public List<SysUser> findForListFromMap(Map<String, Object> map) {
        List<SysUser> result = super.findForListFromMap(map);
//		super.transListSingleField(result, "cityCode", "cityName", "6100000");
        return result;
    }

    /**
     * 根据父Id分组构建json数据
     *
     * @param menuList
     * @return
     */

    public JSONArray searchArrayGroupby(List<SysMenu> menuList) {
        menuList = dropDoulbe(menuList);
        MyMap<String, JSONObject> tempmap = new MyMap<String, JSONObject>();
        MyMap<String, JSONObject> mapobj = new MyMap<String, JSONObject>();
        MyMap<String, Integer> map = caseListToMap(menuList);
        for (Integer item : map.getValueList()) {
            JSONArray temparr = new JSONArray();
            List<SysMenu> templist = seachChildJson(item, menuList);
            for (SysMenu menu : templist) {
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
    private MyMap<String, Integer> caseListToMap(List<SysMenu> menuList) {
        MyMap<String, Integer> map = new MyMap<String, Integer>();
        for (SysMenu item : menuList) {
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
    private List<SysMenu> seachChildJson(Integer parentId, List<SysMenu> menuList) {
        List<SysMenu> array = new ArrayList<SysMenu>();
        for (SysMenu item : menuList) {
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
        SysMenu parentmenu = selectParentMenuByid(map);
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
    public SysMenu selectParentMenuByid(Map<String, Object> map) {
        return sysUserDAO.selectParentMenuByid(map);
    }

    /**
     * 校验密码
     */
    @Override
    public boolean validataPass(SysUser adminUser) {
        if (adminUser.getOldPassword() == null) {
            return false;
        }
        adminUser.setPassword(ENCodeUtils.encodeByMD5(adminUser.getOldPassword()).toLowerCase());
        int count = sysUserDAO.validataPass(adminUser);
        return count > 0;
    }

    /**
     * 验证登录名是否存在
     */
    @Override
    public boolean validataLoginName(SysUser adminUser) {
        int count = sysUserDAO.getAdminUserCountByLoginName(adminUser);
        return count <= 0;
    }

    /**
     * 更新密码
     */
    @Override
    public boolean updatePass(SysUser adminUser) {
        if (adminUser.getOldPassword() == null) {
            return false;
        }
        int count = sysUserDAO.validataPass(adminUser);
        if (count > 0) {
            if (adminUser.getNewPassword() == null) {
                return false;
            }
            adminUser.setPassword(adminUser.getNewPassword());
            count = sysUserDAO.updatePass(adminUser);
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
    public List<SysMenu> Test(Map<String, Object> map) {
        return sysUserDAO.readMenuByIds(map);
    }

    /**
     * 获取用户操作权限
     */
    @Override
    public List<SysMenuPermission> searchUserButton(SysUser adminUser) {
        return sysUserDAO.searchUserButton(adminUser);
    }

    /**
     * 根据用户获取菜单，shiro授权使用
     */
    @Override
    public List<String> selectMenuByUname(SysUser adminUser) {
        return selectMenuByUname(adminUser, SHOW);
    }

    @Override
    public List<String> selectMenuByUname(SysUser adminUser, int menuState) {
        List<SysMenu> adminMenus = null;
        SysUser tempUser = selectUserByULname(adminUser);
        if (tempUser == null) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (tempUser.getIsAdmin() == ADMIN)// 管理员时，全查
        {
            paramMap.put("menuState", menuState);
            adminMenus = sysMenuDAO.findForAllList(paramMap);
        } else {
            paramMap = MapUtils.bean2Map(adminUser);
            paramMap.put("menuState", menuState);
            adminMenus = sysUserDAO.selectMenuByUname(paramMap);
        }
        List<String> resulstList = readButtonsByList(adminMenus);
        return resulstList;
    }

    private List<String> readButtonsByList(List<SysMenu> adminMenus) {
        List<String> resulstList = new ArrayList<String>();
        for (SysMenu item : adminMenus) {
            resulstList.add(item.getNamespace());
        }
        return resulstList;
    }

    @Override
    public boolean delete(SysUser bean) {
        deleteUserRole(bean);
        return super.delete(bean);
    }

    @Override
    public SysUser selectUserByULname(SysUser adminUser) {
        return sysUserDAO.selectUserByULname(adminUser);
    }

    @Override
    public Map<String, Object> checkOperatorLogin(Map<String, Object> paramMap) {
        paramMap.put("password", ENCodeUtils.encodeByMD5(ConverterUtils.toString(paramMap.get("password"))).toLowerCase());
        return sysUserDAO.checkOperatorLogin(paramMap);
    }

    @Override
    public SysUser findUserByName(String userName) {
        SysUser adminUser = new SysUser();
        adminUser.setUserLoginName(userName);
        return selectUserByULname(adminUser);
    }

    @Override
    public List<String> findMenuButtonByName(String userName) {
        SysUser adminUser = new SysUser();
        adminUser.setUserLoginName(userName);
        return selectMenuByUname(adminUser);
    }

    @Override
    public HttpResult refreshRedisCache() {
        List<SysUser> userList = this.select();
        userList.forEach(sysUser -> {
            if (!StringUtil.isEmpty(sysUser.getUserName())) {
                redisCacheService.remove("ucenter:sysuser:username:" + sysUser.getUserId());
                redisCacheService.remove("ucenter:sysuser:userheader:" + sysUser.getUserId());
                redisCacheService.addStr("ucenter:sysuser:username:"+ sysUser.getUserId(), sysUser.getUserName());
                redisCacheService.addStr("ucenter:sysuser:userheader:"+ sysUser.getUserId(), EConfig.getPathPropertiesValue("fhs_file_basePath") + "/downLoad/file?fileId=" + sysUser.getHeader());
            }
        });
        return HttpResult.success();
    }

    @Override
    public List<LeftMenu> getMenu(SysUser user, String menuType) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //如果是saas模式需要判断菜单类型
        if(ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel")))
        {
            //如果不是运营者的集团编码，只能是租户，如果是运营者的编码可以按照参数请求的来
            if(EConfig.getOtherConfigPropertiesValue("operator_group_code").equals(user.getGroupCode()))
            {
                paramMap.put("menuType", menuType);
            }
            else
            {
                paramMap.put("menuType", SysMenuService.MENU_TYPE_TENANT);
                if(ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isSaasModel")))
                {
                    String systemIds = tenantService.selectBean(UcenterMsTenant.builder().groupCode(user.getGroupCode()).build()).getSystemIds();
                    if(systemIds!=null)
                    {
                        paramMap.put("systemIds", StringUtil.getStrToIn(systemIds.split(",")));
                    }
                }
            }
        }
        List<SysMenu> menuList = sysUserDAO.selectMenuAll(paramMap);
        menuList = menuFilter(user, menuList);
        Map<Integer, LeftMenu> leftMenuMap = new HashMap<>();
        // 遍历AdminMenu转换为LeftMenu
        menuList.forEach(adminMenu -> {
            LeftMenu leftMenu = new LeftMenu()
                    .mk("id", adminMenu.getMenuId(), "serverUrl", adminMenu.getServerUrl(), "name", adminMenu.getMenuName(), "url", adminMenu.getMenuUrl(), "menuServer", adminMenu.getServerNameId(), "image", adminMenu.getImage());
            leftMenuMap.put(leftMenu.getId(), leftMenu);
        });
        List<LeftMenu> result = new ArrayList<>();
        menuList.forEach(adminMenu -> {
            if (ConverterUtils.toInt(adminMenu.getMenuState()) != SysMenuService.NOT_SHOW) {
                // 如果不是null 也不是root则找爸爸吧自己添加到爸爸的儿子里面去
                if (adminMenu.getFatherMenuId() != null && adminMenu.getFatherMenuId() != ROOT) {
                    if (leftMenuMap.containsKey(adminMenu.getFatherMenuId())) {
                        leftMenuMap.get(adminMenu.getFatherMenuId()).getSonMenu().add(
                                leftMenuMap.get(adminMenu.getMenuId()));
                    }
                }
                // 如果是一级菜单则挂写到result去
                else if (adminMenu.getFatherMenuId() != null && adminMenu.getFatherMenuId() == ROOT) {
                    result.add(leftMenuMap.get(adminMenu.getMenuId()));
                }
            }
        });
        return result;
    }


    private List<SysMenu> menuFilter(SysUser user, List<SysMenu> menuList) {
        List<Integer> userMenuIds = null;
        if (user.getIsAdmin() == ADMIN) {
            userMenuIds = sysUserDAO.selectMenuIdByAdmin(user);
        } else {
            userMenuIds = sysUserDAO.selectMenuIdByUserId(user);
        }

        Map<Integer, SysMenu> menuMap = new HashMap<>();
        menuList.forEach(menu -> {
            menuMap.put(menu.getMenuId(), menu);
        });
        // 已经添加进结果的菜单
        Set<SysMenu> hasAddMenu = new HashSet<>();
        userMenuIds.forEach(id -> {
            if (menuMap.containsKey(id)) {
                hasAddMenu.add(menuMap.get(id));
                // 能看到儿子就能看到爸爸，找儿子的爸爸
                initFather(hasAddMenu, menuMap, menuMap.get(id));
            }
        });
        List<SysMenu> result = new ArrayList<>();
        result.addAll(hasAddMenu);
        result.sort(new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
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

    private void initFather(Set<SysMenu> hasAddMenu, Map<Integer, SysMenu> menuMap, SysMenu sysMenu) {
        if (menuMap.containsKey(sysMenu.getFatherMenuId())) {
            SysMenu father = menuMap.get(sysMenu.getFatherMenuId());
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
    public SysUser findSysUserById(String userId) {
        //根据id获取用户信息
        SysUser sysUser = this.findBeanById(userId);
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
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        return this.deleteUserRole(sysUser);
    }

    @Override
    public Integer findUserByOrgId(Map<String, Object> paramMap) {
        return sysUserDAO.findUserByOrgId(paramMap);
    }

    @Override
    public List<String> getPermissionUrl(SysUser sysUser) {
        // 如果是admin则返回所有的url
        if (sysUser.getIsAdmin() == SysUserService.SYS_USER_IS_ADMIN) {
            return getPermissionUrlAll();
        }
        return getPermissionUrlByUserId(sysUser.getUserId());

    }

    @Override
    public Map<String, String> findUserDataPermissions(String userId) {
        List<SysRole> roleList = roleService.findRolesByUserId(userId);
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
        SysUser user = this.selectById(userId);
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
    public List<SysUserOrgDTO> getUserOrgTreeList(String groupCode) {
        List<SysUserOrgDTO> dbRecord = sysUserDAO.getUserOrgTreeList(groupCode);

        //找不到爸爸的才会放到此里面
        List<SysUserOrgDTO> result = new ArrayList<>();

        Map<String,SysUserOrgDTO> fatherDTO = new HashMap<>();
        for(SysUserOrgDTO user : dbRecord)
        {
            fatherDTO.put(user.getId(),user);
            if(fatherDTO.containsKey(user.getParentId()))
            {
                fatherDTO.get(user.getParentId()).getChildren().add(user);
            }
            else{
                result.add(user);
            }
        }
        return result;
    }


    private List<String> getPermissionUrlByUserId(String userId) {
        return sysUserDAO.getPermissionUrlByUserId(userId);
    }

    private List<String> getPermissionUrlAll() {
        return sysUserDAO.getPermissionUrlAll();
    }

}
