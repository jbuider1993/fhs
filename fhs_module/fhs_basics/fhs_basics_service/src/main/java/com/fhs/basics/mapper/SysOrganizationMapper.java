package com.fhs.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhs.basics.dox.SysOrganizationDO;
import com.fhs.basics.vo.ComboboxNodeVO;
import com.fhs.basics.vo.TreeModelVO;
import com.fhs.core.base.mapper.FhsBaseMapper;
import com.mybatis.jpa.annotation.MapperDefinition;
import com.mybatis.jpa.annotation.MultiTenancyCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @Description:后台组织机构表 Mapper 接口
 * @author  qixiaobo
 * @version [版本号, 2018-09-04]
 * @versio 1.0 陕西小伙伴网络科技有限公司  Copyright (c) 2018 All Rights Reserved.
 */
@MultiTenancyCheck
@MapperDefinition(domainClass = SysOrganizationDO.class)
public interface SysOrganizationMapper extends FhsBaseMapper<SysOrganizationDO> {

    /**
     * 获取机构ID combobox格式
     * @param userOrgId 当前用户机构id
     * @param parentId 父机构id
     * @return combotree 数据
     */
    List<ComboboxNodeVO> getSubNodeDatas(@Param("userOrgId") String userOrgId, @Param("parentId") String parentId);

    /**
     * 根据父id获取树集合
     * @param map 条件列表
     * @return
     */
    List<TreeModelVO> getTreesData(Map<String, Object> map);

    /**
     * 根据名称和父编号查询机构数量
     * @param sysOrganization 机构
     * @return
     */
    int findCountByNameAndParentId(SysOrganizationDO sysOrganization);

    /**
     * 根据条件查询机构数
     * @param paramMap 查询条件
     * @return 机构数
     */
    Integer findChildCountById(Map<String, Object> paramMap);


    /**
     * 查询当前最大的同爸爸排行是第几
     * @param parentId 父亲id
     * @return 当前最大的排行
     */
    Integer findRank(@Param("parentId") String parentId);
}
