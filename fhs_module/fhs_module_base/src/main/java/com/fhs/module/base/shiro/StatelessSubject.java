package com.fhs.module.base.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义subject
 */
public class StatelessSubject extends WebDelegatingSubject implements Serializable {
    public StatelessSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
                            ServletRequest request, ServletResponse response, SecurityManager securityManager) {
        super(principals, authenticated, host, session, request, response, securityManager);
        // TODO Auto-generated constructor stub
    }

    public StatelessSubject(WebDelegatingSubject subject) {
        super(subject.getPrincipals(), subject.isAuthenticated(), subject.getHost(), null, null, null, subject.getSecurityManager());
        // TODO Auto-generated constructor stub
    }

    public WebDelegatingSubject coverToSuper() {
        return new WebDelegatingSubject(this.principals, this.authenticated, this.host, null, null, null, this.securityManager);
    }
}