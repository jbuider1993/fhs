package com.fhs.shiro;

import com.fhs.redis.service.RedisCacheService;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 没有状态的权限管理器
 */
public class StatelessSecurityManager extends DefaultWebSecurityManager {

    @Autowired
    private RedisCacheService<Subject> redisCacheService;

    @Override
    public Subject createSubject(SubjectContext subjectContext) {
        SessionKey sessionKey = getSessionKey(subjectContext);
        if (WebUtils.isHttp(sessionKey)) {
            ServletRequest request = WebUtils.getRequest(sessionKey);
            ServletResponse response = WebUtils.getResponse(sessionKey);
            String token = request.getParameter("token");
            if (token != null) {
                try {
                    Subject subjectCache = redisCacheService.get("shiro:" + token);
                    if (subjectCache != null) {
                        Subject subject = super.createSubject(subjectContext);
                        WebDelegatingSubject subject2 = (WebDelegatingSubject) subject;
                        return new WebDelegatingSubject(subjectCache.getPrincipals(), subjectCache.isAuthenticated(),
                                subject2.getHost(), subject2.getSession(), request, response,
                                subject2.getSecurityManager());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return super.createSubject(subjectContext);
    }

}