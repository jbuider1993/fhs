package com.fhs.ucenter.service.impl;

import com.fhs.common.utils.Logger;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.core.strategy.GenInfo;
import com.fhs.ucenter.bean.SysRole;
import com.fhs.ucenter.dao.SysRoleDAO;
import com.fhs.ucenter.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
@DataSource("base_business")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService
{

    private static final Logger LOG = Logger.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleDAO dao;

    /**
     * 添加角色信息
     */
    @GenInfo
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addRole(SysRole adminRole)
    {
        // 插入角色信息
        int count = dao.insertJpa(adminRole);
        if (count > 0)
        {
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
    public boolean saveButtons(SysRole adminRole)
    {
        if (adminRole.getMethods().length > 0)
        {
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
    private String[] buildButtonArray(String[] buttons)
    {
        List<String> list = new ArrayList<String>();
        for (String item : buttons)
        {

            String[] itemarr = item.split("_");
            if ("t".equalsIgnoreCase(itemarr[0]))
            {
                String buttonId = itemarr[1].split(":")[1];
                list.add(buttonId);
            }
            else
            {
                String buttonType = itemarr[1].split(":")[1];
                String menuId = itemarr[1].split(":")[0];
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("buttonType", buttonType);
                map.put("menuId", menuId);
                List<String> itemlist = searchButtonId(map);
                list.addAll(itemlist);
            }
        }
        return list.toArray(new String[] {});
    }

    /***
     * 添加角色对应的操作信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addButtons(SysRole adminRole)
    {
        int count = dao.addButtons(adminRole);
        return (count > 0);
    }

    /**
     * 删除角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteButtons(SysRole adminRole)
    {
        try
        {
            dao.deleteButtons(adminRole);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("删除关联按钮数据错误",e);
            return false;
        }
    }

    /**
     * 删除角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteRole(SysRole adminRole)
    {
        // 删除按钮信息
        boolean count = deleteButtons(adminRole);
        if (count)
        {
            // 删除角色用户关联
            dao.deleteUserRela(adminRole);
            // 删除角色信息
            return  dao.delete(adminRole) > 0;
        }
        return false;
    }

    /**
     * 更新角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateRole(SysRole adminRole)
    {
        // 删除当前角色的按钮信息
        boolean count = deleteButtons(adminRole);
        if (count)
        {
            // 修改当前角色信息
            int result = dao.updateSelectiveById(adminRole);
            if (result > 0)
            {
                if (adminRole.getMethods().length > 0)
                {
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
    public List<Map<String, Object>> searchButtons(SysRole adminRole)
    {
        return dao.searchButtons(adminRole);
    }

    /**
     * 查询按钮id列表
     */
    @Override
    public List<String> searchButtonId(Map<String, Object> map)
    {
        return dao.searchButtonId(map);
    }

    /**
     * 通过角色名称获取角色
     */
    @Override
    public List<SysRole> findRoleByGroupCode(Map<String, Object> map)
    {
        return this.dao.findRoleByGroupCode(map);
    }

    /**
     * 根据用户获取角色
     */
    @Override
    public List<SysRole> findRolesByUserId(String userId)
    {
        return dao.findRolesByUserId(userId);
    }

    @Override
    public Integer findUserCountByRoleId(Map<String, Object> paramMap) {
        return dao.findUserCountByRoleId(paramMap);
    }

}
