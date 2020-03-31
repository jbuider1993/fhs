package com.fhs.basics.service.impl;

import com.fhs.basics.api.rpc.FeignSysRoleApiService;
import com.fhs.basics.dox.UcenterMsRoleDO;
import com.fhs.basics.mapper.UcenterMsRoleMapper;
import com.fhs.basics.service.UcenterMsRoleService;
import com.fhs.basics.service.UcenterMsUserService;
import com.fhs.basics.vo.UcenterMsRoleVO;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.ListUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色实现
 * 老代码待优化
 */
@Primary
@Service
@DataSource("base_business")
public class UcenterMsRoleServiceImpl extends BaseServiceImpl<UcenterMsRoleVO, UcenterMsRoleDO> implements UcenterMsRoleService, FeignSysRoleApiService {

    private static final Logger LOG = Logger.getLogger(UcenterMsRoleServiceImpl.class);

    @Autowired
    private UcenterMsRoleMapper mapper;

    /**
     * 后台用户服务
     */
    private UcenterMsUserService userService;

    /**
     * 添加角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addRole(UcenterMsRoleDO adminRole) {
        // 插入角色信息
        int count = mapper.insertJpa(adminRole);
        if (count > 0) {
            return saveButtons(adminRole);
        }
        return false;
    }

    /**
     * 添加角色的权限信息
     *
     * @param adminRole
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveButtons(UcenterMsRoleDO adminRole) {
        if (adminRole.getMethods().length > 0) {
            // 构建按钮列表
            adminRole.setMethods(buildButtonArray(adminRole.getMethods()));

            // 添加角色按钮信息
            return addButtons(adminRole);
        }
        return true;
    }

    /**
     * 构建按钮列表集合
     *
     * @param buttons
     * @return
     */
    private String[] buildButtonArray(String[] buttons) {
        List<String> list = new ArrayList<String>();
        for (String item : buttons) {

            String[] itemarr = item.split("_");
            if ("t".equalsIgnoreCase(itemarr[0])) {
                String buttonId = itemarr[1].split(":")[1];
                list.add(buttonId);
            } else {
                String buttonType = itemarr[1].split(":")[1];
                String menuId = itemarr[1].split(":")[0];
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("buttonType", buttonType);
                map.put("menuId", menuId);
                List<String> itemlist = searchButtonId(map);
                list.addAll(itemlist);
            }
        }
        return list.toArray(new String[]{});
    }

    /***
     * 添加角色对应的操作信息
     */
    @Override
    public boolean addButtons(UcenterMsRoleDO adminRole) {
        int count = mapper.addButtons(adminRole);
        return (count > 0);
    }

    /**
     * 删除角色信息
     */
    @Override
    public boolean deleteButtons(UcenterMsRoleDO adminRole) {
        try {
            mapper.deleteButtons(adminRole);
            return true;
        } catch (Exception e) {
            LOG.error("删除关联按钮数据错误", e);
            return false;
        }
    }

    /**
     * 删除角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteRole(UcenterMsRoleDO adminRole) {
        // 删除按钮信息
        boolean count = deleteButtons(adminRole);
        if (count) {
            // 删除角色用户关联
            mapper.deleteUserRela(adminRole);
            // 删除角色信息
            return mapper.deleteBean(adminRole) > 0;
        }
        return false;
    }

    /**
     * 更新角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateRole(UcenterMsRoleDO adminRole) {
        // 删除当前角色的按钮信息
        boolean count = deleteButtons(adminRole);
        if (count) {
            // 修改当前角色信息
            int result = mapper.updateSelectiveById(adminRole);
            if (result > 0) {
                if (adminRole.getMethods().length > 0) {
                    // 构建按钮列表
                    adminRole.setMethods(buildButtonArray(adminRole.getMethods()));
                    // 插入按钮信息
                    return addButtons(adminRole);
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 查询角色的按钮信息
     */
    @Override
    public List<Map<String, Object>> searchButtons(UcenterMsRoleDO adminRole) {
        return mapper.searchButtons(adminRole);
    }

    /**
     * 查询按钮id列表
     */
    @Override
    public List<String> searchButtonId(Map<String, Object> map) {
        return mapper.searchButtonId(map);
    }

    /**
     * 通过角色名称获取角色
     */
    @Override
    public List<UcenterMsRoleVO> findRoleByGroupCode(Map<String, Object> map) {
        return ListUtils.copyListToList(this.mapper.findRoleByGroupCode(map), UcenterMsRoleVO.class);
    }

    /**
     * 根据用户获取角色
     */
    @Override
    public List<UcenterMsRoleVO> findRolesByUserId(String userId) {
        return ListUtils.copyListToList(mapper.findRolesByUserId(userId), UcenterMsRoleVO.class);
    }

    @Override
    public Integer findUserCountByRoleId(Map<String, Object> paramMap) {
        return mapper.findUserCountByRoleId(paramMap);
    }

    @Override
    public HttpResult<String> getRoleListPermissions(String userId) {
        if (CheckUtils.isNullOrEmpty(userId)) {
            return HttpResult.error(null, "用户ID不可为空");
        }
        List<UcenterMsRoleVO> roles = this.findRolesByUserId(userId);
        if (userService == null) {
            userService = SpringContextUtil.getBeanByClass(UcenterMsUserService.class);
        }
        UcenterMsUserVO sysUser = userService.selectById(userId);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (UcenterMsRoleVO sysRole : roles) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", sysRole.getRoleId());
            map.put("userId", sysUser.getUserId());
            map.put("fullname", sysUser.getUserName());
            map.put("roleName", sysRole.getRoleName());
            map.put("alias", sysRole.getRoleId());
            mapList.add(map);
        }
        return HttpResult.success(JsonUtils.list2json(mapList));
    }

    @Override
    public HttpResult<String> getRoleById(String id) {
        if (CheckUtils.isNullOrEmpty(id)) {
            return HttpResult.error(null, "角色ID不可为空");
        }
        UcenterMsRoleVO sysRole = this.findBeanById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", sysRole.getRoleId());
        map.put("roleName", sysRole.getRoleName());
        map.put("isDisable", sysRole.getRoleName());
        map.put("alias", sysRole.getRoleId());
        return HttpResult.success(JsonUtils.object2json(map));
    }
}
