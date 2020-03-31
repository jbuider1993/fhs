package com.fhs.basics.service.impl;

import com.fhs.basics.dox.SettMsMenuPermissionDO;
import com.fhs.basics.dox.SettMsMenuPermissionUrlRelaDO;
import com.fhs.basics.mapper.SettMsMenuPermissionMapper;
import com.fhs.basics.service.SettMsMenuPermissionService;
import com.fhs.basics.vo.SettMsMenuPermissionUrlRelaVO;
import com.fhs.basics.vo.SettMsMenuPermissionVO;
import com.fhs.common.utils.ListUtils;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.ds.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@DataSource("base_business")
public class SettMsMenuPermissionServiceImpl extends BaseServiceImpl<SettMsMenuPermissionVO, SettMsMenuPermissionDO>
        implements SettMsMenuPermissionService {
    @Autowired
    private SettMsMenuPermissionMapper mapper;

    /**
     * 根据类型查询数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> findMapListByType(Map<String, Object> map) {
        return mapper.findMapListByType(map);
    }

    @Override
    public boolean addBaseMenuBatch(Map<String, Object> map) {
        if (map.containsKey("menuId")) {

            List<SettMsMenuPermissionDO> existsButtonList = mapper.findForListFromMap(map);
            Map<String, Map<String, Object>> baseButtonMap = this.getBaseButton();
            String buttonMethod;
            for (SettMsMenuPermissionDO button : existsButtonList) {
                buttonMethod = button.getMethod();
                if (baseButtonMap.containsKey(buttonMethod)) {
                    baseButtonMap.remove(buttonMethod);
                }
            }
            if (baseButtonMap.size() > 0) {
                map.put("baseButtonList", new ArrayList<Map<String, Object>>(baseButtonMap.values()));
                // 一键添加增删改查菜单
                mapper.addBaseMenuBatch(map);
            }
        }
        return true;
    }

    @Override
    public List<SettMsMenuPermissionUrlRelaVO> getUrlByPermissionId(SettMsMenuPermissionDO sysMenuPermission) {
        return ListUtils.copyListToList(mapper.getUrlByPermissionId(sysMenuPermission), SettMsMenuPermissionUrlRelaVO.class);
    }

    @Override
    public Integer getUrlCoutByPermissionId(SettMsMenuPermissionDO sysMenuPermission) {
        return mapper.getUrlCoutByPermissionId(sysMenuPermission);
    }

    @Override
    public boolean addUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela) {
        Integer count = mapper.addUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    @Override
    public boolean updateUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela) {
        Integer count = mapper.updateUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    @Override
    public boolean delUrl(SettMsMenuPermissionUrlRelaDO sysMenuPermissionUrlRela) {
        Integer count = mapper.delUrl(sysMenuPermissionUrlRela);
        return count > 0;
    }

    /**
     * 获取基础权限的数据，组装为map，key为权限，value为权限的信息
     *
     * @return
     */
    private Map<String, Map<String, Object>> getBaseButton() {
        String[][] buttonArray = {
                {"see", "查询", "1"},
                {"add", "添加", "2"},
                {"update", "修改", "3"},
                {"del", "删除", "4"}
        };
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        Map<String, Object> tempMap;
        for (int i = 0; i < buttonArray.length; i++) {
            tempMap = new HashMap<>();
            tempMap.put("method", buttonArray[i][0]);
            tempMap.put("permissionName", buttonArray[i][1]);
            tempMap.put("permissionType", buttonArray[i][2]);
            resultMap.put(buttonArray[i][0], tempMap);
        }
        return resultMap;
    }

}
