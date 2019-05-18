package com.fhs.config;

import com.fhs.pagex.filter.PageXFilter;
import com.fhs.shiro.ShiroCasRealm;
import com.fhs.shiro.ShiroRealm;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.realm.Pac4jRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置包含单点登录支持
 */
@Configuration
public class ShiroConfiguration{

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
        casClient.setName(clientName);
        final Clients clients = new Clients(shiroServerUrlPrefix + "/callback?client_name=" + clientName, casClient);
        final Config config = new Config(clients);
        return config;
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

    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    @DependsOn("shiroRealm")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(ehCacheManager());// 用户授权/认证信息Cache, 采用EhCache 缓存
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
        }
        shiroFilterFactoryBean.setFilters(filtersMap);
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
            filterChainDefinitionMap.put("/callback", "casFilter");
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
