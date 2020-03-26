package com.fhs.basics.service.impl;

import com.fhs.basics.constant.BaseTransConstant;
import com.fhs.basics.dox.SysOrganizationDO;
import com.fhs.basics.mapper.SysOrganizationMapper;
import com.fhs.basics.service.SysOrganizationService;
import com.fhs.basics.vo.ComboboxNodeVO;
import com.fhs.basics.vo.SysOrganizationVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.result.HttpResult;
import com.fhs.core.trans.anno.AutoTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeModel;
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
@AutoTrans(namespace = BaseTransConstant.ORG,fields = "name",useRedis = true)
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganizationVO, SysOrganizationDO> implements SysOrganizationService {

    @Autowired
    private SysOrganizationMapper mapper;

    /**
     * @desc 注入redisde的service对象
     */
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public boolean insertOrganization(SysOrganizationDO adminOrganization) {
        String parentId = adminOrganization.getParentId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        Integer ranking = mapper.findRank(parentId);
        ranking = ranking == null ? 0 : ranking;
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

    /**
     * @return httpResult
     * @desc 刷新所有机构缓存
     */
    @Override
    public HttpResult<Map> refreshRedisCache() {
        List<SysOrganizationVO> sysOrganizationList = this.select();
        sysOrganizationList.forEach(sysOrganization -> {
            if (!StringUtil.isEmpty(sysOrganization.getName())) {
                //删除redis的当前数据
                redisCacheService.remove(BaseTransConstant.SYS_ORGANIZATION_NAME + sysOrganization.getId());
                //重新放入redis中
                redisCacheService.addStr(BaseTransConstant.SYS_ORGANIZATION_NAME + sysOrganization.getId(), sysOrganization.getName());
            }
        });
        return HttpResult.success();
    }

    @Override
    public boolean validataOrgName(SysOrganizationDO sysOrganization) {
        int count = mapper.findCountByNameAndParentId(sysOrganization);
        return count <= 0;
    }

    @Override
    public Integer findChildCountById(Map<String, Object> paramMap) {
        return mapper.findChildCountById(paramMap);
    }

}
