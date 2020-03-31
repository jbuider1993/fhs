package com.fhs.module.base.config;


import com.fhs.module.base.shiro.ShiroCasRealm;
import com.fhs.module.base.shiro.ShiroRealm;
import com.fhs.module.base.shiro.StatelessSecurityManager;
import com.fhs.module.base.shiro.cache.ShiroSpringCacheManager;
import com.fhs.pagex.filter.PageXFilter;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.realm.Pac4jRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.logout.DefaultCasLogoutHandler;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置包含单点登录支持
 */
@Configuration
@Order(2)
public class ShiroConfiguration {

    /*
     *cas server地址
     */
    @Value("${fhs.login.cas.casServerUrlPrefix:}")
    private String casServerUrlPrefix;

    /*
     * 当前工程对外提供的服务地址
     */
    @Value("${fhs.login.cas.shiroServerUrlPrefix:}")
    private String shiroServerUrlPrefix;

    /*
     * 当前工程对外提供的服务地址
     */
    @Value("${fhs.usevue:false}")
    private boolean useVue;

    private String clientName = "sso";


    @Value("${fhs.login.url:http://default.fhs-opensource.com}")
    private String shrioLoginUrl;

    @Value("${fhs.login.enable-cas}")
    private boolean isEnableCas;

    /*
     *   权限认证失败跳转地址
     */
    public static final String unauthorizedUrl = "/error/403.html";

    @Bean
    public Config config() {
        // CAS
        final CasConfiguration configuration = new CasConfiguration(casServerUrlPrefix + "/login", casServerUrlPrefix);
        configuration.setAcceptAnyProxy(true);
        CasClient casClient = new CasClient(configuration);
        casClient.setCallbackUrl(shiroServerUrlPrefix + "/callback?client_name=" + clientName);
        if(isEnableCas)
        {
            configuration.setLogoutHandler(shiroCasLogoutHandler());
        }
        casClient.setName(clientName);
        final Clients clients = new Clients(shiroServerUrlPrefix + "/callback?client_name=" + clientName, casClient);
        final Config config = new Config(clients);
        return config;
    }

    /**
     * 登出操作
     * @return
     */
    public DefaultCasLogoutHandler shiroCasLogoutHandler(){
        DefaultCasLogoutHandler logoutHandler = new DefaultCasLogoutHandler();
        return logoutHandler;
    }

    @Bean(name = "shiroRealm")
    public AuthorizingRealm shiroRealm() {
        if (isEnableCas) {
            //使用MD5加密
            HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
            hashedCredentialsMatcher.setHashAlgorithmName("MD5");
            hashedCredentialsMatcher.setHashIterations(1024);
            Pac4jRealm shiroRealms = new ShiroCasRealm();
            //shiroRealms.setCredentialsMatcher(hashedCredentialsMatcher);
            return shiroRealms;
        }
        return new ShiroRealm();
    }



    /**
     * 自定义的缓存管理器
     * @return  自定义缓存管理器
     */
    @Bean(name = "shiroSpringCacheManager")
    @DependsOn("redisConfig")
    public ShiroSpringCacheManager shiroSpringCacheManager(RedisCacheManager redisCacheManager) {
        ShiroSpringCacheManager cacheManager = new ShiroSpringCacheManager();
        cacheManager.setCacheManager(redisCacheManager);
        return cacheManager;
    }

    @Bean(name = "securityManager")
    @DependsOn({"shiroRealm","shiroSpringCacheManager","redisCacheManager"})
    public DefaultWebSecurityManager securityManager(RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        if(this.useVue)
        {
            securityManager = new StatelessSecurityManager();
        }
        securityManager.setCacheManager(shiroSpringCacheManager(redisCacheManager));// 用户授权/认证信息Cache, 采用EhCache 缓存
       /*if (isEnableCas) {
            securityManager.setSubjectFactory(new CasSubjectFactory());
        }*/
       // SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(shiroRealm());
        return securityManager;
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
        String realLoginUrl = this.isEnableCas ? casServerUrlPrefix + "/login?service=" +  shiroServerUrlPrefix + "/callback?client_name=" + clientName : shrioLoginUrl;
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
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        /*
         *  添加casFilter到shiroFilter中，注意，casFilter需要放到shiroFilter的前面，
         *  从而保证程序在进入shiro的login登录之前就会进入单点认证
         */
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("pagexFilter",new PageXFilter());
        if(isEnableCas)
        {
            //设置pac4j回调Filter
            CallbackFilter callbackFilter = new CallbackFilter();
            callbackFilter.setConfig(config());
            //callbackFilter.setDefaultUrl("/starter");
            filtersMap.put("casFilter", callbackFilter);
            // 注销 拦截器
            LogoutFilter logoutFilter = new LogoutFilter();
            logoutFilter.setConfig(config());
            logoutFilter.setCentralLogout(true);
            logoutFilter.setLocalLogout(true);
            logoutFilter.setDefaultUrl(shiroServerUrlPrefix + "/callback?client_name=" + clientName);
            filtersMap.put("logout",logoutFilter);
        }
        shiroFilterFactoryBean.setFilters(filtersMap);
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(shiroServerUrlPrefix);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        bean.setFilter(singleSignOutFilter);
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        return bean;
    }

    /**
     * 注册单点登出的listener
     *
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean<?> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SingleSignOutHttpSessionListener());
        bean.setEnabled(true);
        return bean;
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
            filterChainDefinitionMap.put("/callback", "casFilter");
            filterChainDefinitionMap.put("/logout", "logout");
        }
        filterChainDefinitionMap.put("/ms/pagex/*", "pagexFilter");
        //2.不拦截的请求
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/page/**", "anon");
        //4.登录过的不拦截
        filterChainDefinitionMap.put("/ms/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }



}
