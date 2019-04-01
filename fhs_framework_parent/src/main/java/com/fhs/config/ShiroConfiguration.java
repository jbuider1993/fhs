package com.fhs.config;

import com.fhs.shiro.ShiroCasRealm;
import com.fhs.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置包含单点登录支持
 */
@Configuration
public class ShiroConfiguration implements InitializingBean {

    /*
     *cas server地址
     */
    @Value("${shiro.cas.casServerUrlPrefix:}")
    private String casServerUrlPrefix;

    /*
     * 当前工程对外提供的服务地址
     */
    @Value("${shiro.cas.shiroServerUrlPrefix:}")
    private String shiroServerUrlPrefix;
    /*
     *  casFilter UrlPattern
     */
    private String casFilterUrlPattern = "/shiro-cas";
    /*
     *  登录地址
     */
    private String loginUrl;

    @Value("${fhs.login.url:http://default.fhs-opensource.com}")
    private String shrioLoginUrl;

    @Value("${fhs.login.enable-cas}")
    private boolean isEnableCas;

    /*
     *   权限认证失败跳转地址
     */
    public static final String unauthorizedUrl = "/error/403.html";



    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm shiroRealm() {
        if (isEnableCas) {
            return new ShiroCasRealm(casServerUrlPrefix, shiroServerUrlPrefix + casFilterUrlPattern);
        }
        return new ShiroRealm();
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    @DependsOn("shiroRealm")
    public DefaultWebSecurityManager securityManager(AuthorizingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(ehCacheManager());// 用户授权/认证信息Cache, 采用EhCache 缓存
        if (isEnableCas) {
            securityManager.setSubjectFactory(new CasSubjectFactory());
        }
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(realm);
        return securityManager;
    }


    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * 注册单点登出的listener
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)// 优先级需要高于Cas的Filter
    public ServletListenerRegistrationBean<?> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SingleSignOutHttpSessionListener());
        bean.setEnabled(true);
        return bean;
    }

    /**
     * 注册单点登出filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        bean.setFilter(new SingleSignOutFilter());
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        return bean;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * 该类可以保证实现了org.apache.shiro.util.Initializable接口的shiro对象的init或者是destory方法被自动调用，
     * 而不用手动指定init-method或者是destory-method方法
     * 注意：如果使用了该类，则不需要手动指定初始化方法和销毁方法，否则会出错
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 下面两个配置主要用来开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    private  CasFilter casFilter;

    /**
     * CAS过滤器
     *
     * @return
     */
    @DependsOn("shiroRealm")
    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(isEnableCas);//理论上来说，开启这个可以启用这个filter关闭的话将不起作用
        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
        casFilter.setFailureUrl(loginUrl);// 我们选择认证失败后再打开登录页面
        casFilter.setLoginUrl(loginUrl);
        this.casFilter = casFilter;
        return casFilter;
    }

    /**
     * 使用工厂模式，创建并初始化ShiroFilter
     *
     * @param securityManager
     * @return
     */
    @DependsOn("shiroRealm")
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        String realLoginUrl = this.isEnableCas ? this.loginUrl : shrioLoginUrl;
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(realLoginUrl);
        /*
         *  登录成功后要跳转的连接，不设置的时候，会默认跳转到前一步的url
         *  比如先在浏览器中输入了http://localhost:8080/userlist,但是现在用户却没有登录，于是会跳转到登录页面，等登录认证通过后，
         *  页面会再次自动跳转到http://localhost:8080/userlist页面而不是登录成功后的index页面
         *  建议不要设置这个字段
         */
//        shiroFilterFactoryBean.setSuccessUrl(loginSuccessUrl);

        // 设置无权限访问页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        /*
         *  添加casFilter到shiroFilter中，注意，casFilter需要放到shiroFilter的前面，
         *  从而保证程序在进入shiro的login登录之前就会进入单点认证
         */

        if(isEnableCas)
        {
            Map<String, Filter> filters = new LinkedHashMap<>();
            filters.put("casFilter", casFilter);
            // logout已经被单点登录的logout取代
            // filters.put("logout",logoutFilter());
            shiroFilterFactoryBean.setFilters(filters);
        }
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）,角色/权限信息由MyShiroCasRealm对象提供doGetAuthorizationInfo实现获取来的
     * 生产中会将这部分规则放到数据库中
     *
     * @param shiroFilterFactoryBean
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        /////////////////////// 下面这些规则配置最好配置到配置文件中，注意，此处加入的filter需要保证有序，所以用的LinkedHashMap ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        if(isEnableCas) {
            filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
        }

        //2.不拦截的请求
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/page/**", "anon");

        //4.登录过的不拦截
        filterChainDefinitionMap.put("/ms/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
    }
}
