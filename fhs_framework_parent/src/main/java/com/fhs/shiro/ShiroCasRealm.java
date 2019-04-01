package com.fhs.shiro;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.Logger;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.service.FeignSysUserApiService;
import com.fhs.ucenter.api.vo.SysUserVo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("deprecation")
public class ShiroCasRealm extends CasRealm {

	final Logger logger = Logger.getLogger(getClass());

	private final static Integer isDisable = 0;

	private  FeignSysUserApiService feignSysUserService;

	public ShiroCasRealm(String casLoginUrl,String casServiceUrl)
	{
	    setCasServerUrlPrefix(casLoginUrl);
        // 客户端回调地址
        setCasService(casServiceUrl);
	}

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
			String loginName = (String) principalcollection.getPrimaryPrincipal();
			HttpResult<SysUserVo> httpResult = feignSysUserService.getSysUserByName(loginName);
			SysUserVo user = httpResult.getData();
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			logger.info("用户已经登录");
			HttpResult<List<String>> menuResult =  feignSysUserService.selectMenuByUname(loginName);
			List<String> listsMenu = menuResult.getData();
			if (listsMenu == null || listsMenu.size() <= 0) {
				return null;
			}
			logger.info(menuResult.asJson());
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addStringPermissions(listsMenu);
			return authorizationInfo;

		} catch (Exception e) {
			logger.error("加载权限错误，用户："+principalcollection.getPrimaryPrincipal(), e);
		}
		return null;

	}
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
