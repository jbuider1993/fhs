package com.fhs.module.base.shiro;

import com.fhs.basics.api.rpc.FeignSysUserApiService;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * cas单点登录器
 */
public class ShiroCasRealm extends Pac4jRealm {

	private static final Logger LOGGER = Logger.getLogger(ShiroCasRealm.class);


	private FeignSysUserApiService feignSysUserService;


	/**
	 * 鎺堟潈
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		if(feignSysUserService==null)
		{
			feignSysUserService = SpringContextUtil.getBeanByClassForApi(FeignSysUserApiService.class);
		}
		try {
			String loginName  = ((Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal()).getProfile().getId();
			HttpResult<UcenterMsUserVO> httpResult = feignSysUserService.getSysUserByName(loginName);
			UcenterMsUserVO user = httpResult.getData();
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			LOGGER.info("用户已经登录");
			HttpResult<List<String>> menuResult =  feignSysUserService.selectMenuByUname(loginName);
			List<String> listsMenu = menuResult.getData();
			if (listsMenu == null || listsMenu.size() <= 0) {
				return null;
			}
			LOGGER.info(menuResult.asJson());
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addStringPermissions(listsMenu);
			return authorizationInfo;

		} catch (Exception e) {
			LOGGER.error("加载权限错误，用户："+principalcollection.getPrimaryPrincipal(), e);
		}
		return null;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	{
		AuthenticationInfo info = super.doGetAuthenticationInfo(token);
		clearCache(info.getPrincipals());
		return info;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection arg0) {
		super.clearCachedAuthorizationInfo(arg0);
	}

}
