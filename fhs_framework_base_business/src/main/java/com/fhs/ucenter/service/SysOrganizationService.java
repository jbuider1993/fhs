package com.fhs.ucenter.service;

import com.fhs.common.bean.ComboboxNode;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.bean.SysOrganization;
import com.fhs.core.base.service.BaseService;
import com.fhs.ucenter.bean.TreeModel;

import java.util.List;
import java.util.Map;

/**
 * @Description:后台组织机构表
 * @author  qixiaobo
 * @version [版本号, 2018-09-04]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
public interface SysOrganizationService extends BaseService<SysOrganization> {

    /**
     * 新增机构
     *
     * @param adminOrganization 后台组织机构数据
     * @return 是否成功
     */
    boolean insertOrganization(SysOrganization adminOrganization);

    /**
     * 获取机构ID combobox格式
     * @param userOrgId 当前用户机构id
     * @param parentId 父机构id
     * @return combotree 数据
     */
    List<ComboboxNode> getSubNode(String userOrgId, String parentId);

    /**
     * 根据父id获取树集合
     * @param map 条件列表
     * @return
     */
    List<TreeModel> getTreesData(Map<String, Object> map);

    /**
     * @desc 刷新所有用户缓存
     * @return httpResult
     */
    HttpResult<Map> refreshRedisCache();

    /**
     * 效验机构名称是否存在
     * @param sysOrganization 机构
     * @return 是否存在
     */
    boolean validataOrgName(SysOrganization sysOrganization);

    /**
     * 根据条件查询机构数
     * @param paramMap 查询条件
     * @return 机构条数
     */
    Integer findChildCountById(Map<String, Object> paramMap);
}
