package com.fhs.ucenter.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.ucenter.bean.SysMenuPermission;
import com.fhs.ucenter.bean.SysMenuPermissionUrlRela;
import com.fhs.ucenter.dao.SysMenuPermissionDAO;
import com.fhs.ucenter.service.SysMenuPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@DataSource("base_business")
public class SysMenuPermissionServiceImpl extends BaseServiceImpl<SysMenuPermission> implements SysMenuPermissionService
{
    @Autowired
    SysMenuPermissionDAO dao;

    /**
     * 根据类型查询数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> findMapListByType(Map<String, Object> map)
    {
        return dao.findMapListByType(map);
    }

    @Override
    public boolean addBaseMenuBatch(Map<String, Object> map)
    {
        if(map.containsKey("menuId")){

            List<SysMenuPermission> existsButtonList = dao.findForListFromMap(map);
            Map<String,Map<String, Object>> baseButtonMap = this.getBaseButton();
            String buttonMethod;
            for(SysMenuPermission button : existsButtonList){
                buttonMethod = button.getMethod();
                if(baseButtonMap.containsKey(buttonMethod)){
                    baseButtonMap.remove(buttonMethod);
                }
            }
            if(baseButtonMap.size() > 0){
                map.put("baseButtonList", new ArrayList<Map<String, Object>>(baseButtonMap.values()));
                // 一键添加增删改查菜单
                dao.addBaseMenuBatch(map);
            }
        }
        return true;
    }

    @Override
    public List<SysMenuPermissionUrlRela> getUrlByPermissionId(SysMenuPermission sysMenuPermission) {
        return dao.getUrlByPermissionId(sysMenuPermission);
    }

    @Override
    public Integer getUrlCoutByPermissionId(SysMenuPermission sysMenuPermission) {
        return dao.getUrlCoutByPermissionId(sysMenuPermission);
    }

    @Override
    public boolean addUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela) {
        Integer count = dao.addUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    @Override
    public boolean updateUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela) {
        Integer count = dao.updateUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    @Override
    public boolean delUrl(SysMenuPermissionUrlRela sysMenuPermissionUrlRela) {
        Integer count = dao.delUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    /**
     *
     * 获取基础权限的数据，组装为map，key为权限，value为权限的信息
     *
     * @return
     */
    private Map<String,Map<String, Object>> getBaseButton(){
        String[][] buttonArray = {
            {"see","查询","1"},
            {"add","添加","2"},
            {"update","修改","3"},
            {"del","删除","4"}
        };
        Map<String,Map<String, Object>> resultMap = new HashMap<>();
        Map<String, Object> tempMap;
        for(int i = 0; i < buttonArray.length; i++){
            tempMap = new HashMap<>();
            tempMap.put("method", buttonArray[i][0]);
            tempMap.put("permissionName", buttonArray[i][1]);
            tempMap.put("permissionType", buttonArray[i][2]);
            resultMap.put(buttonArray[i][0], tempMap);
        }
        return resultMap;
    }

}
