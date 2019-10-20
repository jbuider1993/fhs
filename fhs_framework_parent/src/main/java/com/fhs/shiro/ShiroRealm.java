package com.fhs.shiro;

import com.fhs.common.constant.Constant;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.core.exception.BusinessException;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.FeignSysUserApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * fhs 非cas的时候使用
 * by jackwong
 */
public class ShiroRealm extends AuthorizingRealm
{

    final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    private FeignSysUserApiService feignSysUserService;

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection)
    {
        if(feignSysUserService==null)
        {
            feignSysUserService = SpringContextUtil.getBeanByClassForApi(FeignSysUserApiService.class);
        }
        String loginName = (String)principalcollection.fromRealm(getName()).iterator().next();
        HttpResult<List<String>> listsMenuResult = feignSysUserService.selectMenuByUname(loginName);
        if(listsMenuResult.getCode()!= Constant.HPROSE_SUCCESS_CODE)
        {
            throw new BusinessException("获取菜单信息错误");
        }
        List<String> listsMenu = listsMenuResult.getData();
        if (listsMenu == null || listsMenu.size() <= 0)
        {
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (String string : listsMenu)
        {
            if (string != null)
            {
                info.addStringPermission(string);
            }
        }
        return info;
    }

    /**
     * 实现用户认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken)
        throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationtoken;
        Subject subjects = SecurityUtils.getSubject();
        clearCache(subjects.getPrincipals());
        // 在登录完成之后清楚之前的 授权缓存
        super.clearCachedAuthorizationInfo(subjects.getPrincipals());
        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }

}
