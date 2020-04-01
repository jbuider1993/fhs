package com.fhs.basics.service.impl;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.UcenterMsOrganizationDO;
import com.fhs.basics.mapper.UcenterMsOrganizationMapper;
import com.fhs.basics.service.UcenterMsOrganizationService;
import com.fhs.basics.vo.ComboboxNodeVO;
import com.fhs.basics.vo.UcenterMsOrganizationVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.trans.anno.AutoTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qixiaobo
 * @version [版本号, 2018-09-04]
 * @Description:后台组织机构表
 * @versio 1.0 陕西小伙伴网络科技有限公司 Copyright (c) 2018 All Rights Reserved.
 */
@Service
@DataSource("base_business")
@AutoTrans(namespace = BaseTransConstant.ORG,fields = "name",useRedis = true,defaultAlias = "org")
public class UcenterMsOrganizationServiceImpl extends BaseServiceImpl<UcenterMsOrganizationVO, UcenterMsOrganizationDO> implements UcenterMsOrganizationService {

    @Autowired
    private UcenterMsOrganizationMapper mapper;

    /**
     * @desc 注入redisde的service对象
     */
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public boolean insertOrganization(UcenterMsOrganizationDO adminOrganization) {
        String parentId = adminOrganization.getParentId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        Integer ranking = mapper.findRank(parentId);
        ranking = ranking == null ? 0 : ranking;
        ranking = ranking + 1;
        String id = parentId + StringUtil.formatCountWith0("", "%03d", ranking);
        adminOrganization.setRanking(ranking + "");
        adminOrganization.setId(id);
        int isAdd = super.insertSelective(adminOrganization);
        if (isAdd >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ComboboxNodeVO> getSubNode(String userOrgId, String parentId) {

        //获取当前数据库
        List<ComboboxNodeVO> dbNode = mapper.getSubNodeDatas(userOrgId, null);

        Map<String, ComboboxNodeVO> comboboxNodeMap = new HashMap<>();
        List<ComboboxNodeVO> fatherNode = new ArrayList<>();
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
    public List<TreeModelVO> getTreesData(Map<String, Object> map) {
        return mapper.getTreesData(map);
    }



    @Override
    public boolean validataOrgName(UcenterMsOrganizationDO sysOrganization) {
        int count = mapper.findCountByNameAndParentId(sysOrganization);
        return count <= 0;
    }

    @Override
    public Integer findChildCountById(Map<String, Object> paramMap) {
        return mapper.findChildCountById(paramMap);
    }

}
