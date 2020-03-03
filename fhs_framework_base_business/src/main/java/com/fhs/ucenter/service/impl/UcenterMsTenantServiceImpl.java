package com.fhs.ucenter.service.impl;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.Md5Util;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.ucenter.bean.SysOrganization;
import com.fhs.ucenter.bean.SysUser;
import com.fhs.ucenter.bean.UcenterMsTenant;
import com.fhs.ucenter.service.SysOrganizationService;
import com.fhs.ucenter.service.SysUserService;
import com.fhs.ucenter.service.UcenterMsTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 租户管理(UcenterMsTenant)表服务实现类
 *
 * @author makejava
 * @since 2019-05-15 14:21:04
 */
@Service("ucenterMsTenantService")
public class UcenterMsTenantServiceImpl extends BaseServiceImpl<UcenterMsTenant> implements UcenterMsTenantService {


    @Lazy
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysOrganizationService organizationService;

    @Override
    public int insert(UcenterMsTenant tenant) {
        SysUser adminUser = new SysUser();
        adminUser.setGroupCode(tenant.getGroupCode());
        adminUser.setPassword(Md5Util.MD5(tenant.getGroupCode() + "123456").toLowerCase());
        adminUser.setUserId(StringUtil.getUUID());
        adminUser.setIsAdmin(Constant.INT_TRUE);
        adminUser.setUserName(tenant.getTenantName());
        adminUser.setUserLoginName(tenant.getGroupCode() + "_admin");
        adminUser.setMobile(tenant.getMobile());
        adminUser.setOrganizationId(tenant.getGroupCode() + "_001");
        adminUser.setIsDisable(Constant.INT_FALSE);
        adminUser.preInsert(null);
        sysUserService.insertJpa(adminUser);
        SysOrganization organization = new SysOrganization();
        organization.setId(tenant.getGroupCode() + "_001");
        organization.setName(tenant.getTenantName());
        organization.setRanking("1");
        organization.setIsDisable(Constant.INT_FALSE);
        organization.preInsert(tenant.getCreateUser());
        organization.setParentId("");
        organization.setGroupCode(tenant.getGroupCode());
        organizationService.insert(organization);
        tenant.setId(StringUtil.getUUID());
        return super.insert(tenant);
    }
}