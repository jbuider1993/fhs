package com.fhs.ucenter.service.impl;

import com.fhs.common.bean.ComboboxNode;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.db.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.ucenter.bean.SysOrganization;
import com.fhs.ucenter.bean.TreeModel;
import com.fhs.ucenter.dao.SysOrganizationDAO;
import com.fhs.ucenter.service.SysOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:后台组织机构表
 * @author  qixiaobo
 * @version [版本号, 2018-09-04]
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
@DataSource("base_business")
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganization> implements SysOrganizationService {

    @Autowired
    private SysOrganizationDAO dao;

    /**
     * @desc 注入redisde的service对象
     */
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public boolean insertOrganization(SysOrganization adminOrganization) {
        String parentId = adminOrganization.getParentId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        int ranking = findCountFromMap(map);
        ranking = ranking + 1;
        String id = parentId + StringUtil.formatCountWith0("", "%03d", ranking);
        adminOrganization.setRanking(ranking + "");
        adminOrganization.setId(id);
        int isAdd = super.insert(adminOrganization);
        if (isAdd >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ComboboxNode> getSubNode(String userOrgId, String parentId) {

        //获取当前数据库
        List<ComboboxNode> dbNode = dao.getSubNodeDatas(userOrgId, null);

        Map<String, ComboboxNode> comboboxNodeMap = new HashMap<>();
        List<ComboboxNode> fatherNode = new ArrayList<>();
        dbNode.forEach(node -> {
            comboboxNodeMap.put(node.getWxOrgId(), node);
            node.setState("open");
        });
        dbNode.forEach(node -> {
            //如果我有爸爸，就吧我放到我爸爸的儿子集合中
            if (comboboxNodeMap.containsKey(node.getParentId())) {
                comboboxNodeMap.get(node.getParentId()).getChildren().add(node);
            } else {
                //如果我 找不到爸爸，我就是祖宗
                fatherNode.add(node);
            }
        });
//        String nodeJson = JsonUtils.list2json(fatherNode);
        return fatherNode;
    }

    @Override
    public List<TreeModel> getTreesData(Map<String, Object> map) {
        return dao.getTreesData(map);
    }

    /**
     * @desc 刷新所有机构缓存
     * @return httpResult
     */
    @Override
    public HttpResult<Map> refreshRedisCache() {
        List<SysOrganization> sysOrganizationList = this.select();
        sysOrganizationList.forEach(sysOrganization -> {
            if(!StringUtil.isEmpty(sysOrganization.getName())) {
                //删除redis的当前数据
                redisCacheService.remove(Constant.SYS_ORGANIZATION_NAME + sysOrganization.getId());
                //重新放入redis中
                redisCacheService.addStr(Constant.SYS_ORGANIZATION_NAME + sysOrganization.getId(), sysOrganization.getName());
            }
        });
        return HttpResult.success();
    }

    @Override
    public boolean validataOrgName(SysOrganization sysOrganization) {
        int count = dao.findCountByNameAndParentId(sysOrganization);
        return count <= 0;
    }

    @Override
    public Integer findChildCountById(Map<String, Object> paramMap) {
        return dao.findChildCountById(paramMap);
    }

}
