package com.ranran.core.configuration;


import com.ranran.core.shiro.credential.CredentialsMatcher;
import com.ranran.core.shiro.filter.CurrentUserFilter;
import com.ranran.core.shiro.realm.AuthRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    private Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     *
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm,
                                           @Qualifier("shiroCache") EhCacheManager cacheManager,
                                           @Qualifier("rememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        //用户授权/认证信息Cache, 采用EhCache缓存
        manager.setCacheManager(cacheManager);
        //注入记住我管理器
        manager.setRememberMeManager(cookieRememberMeManager);
        return manager;
    }


    /**
     * shiro缓存管理
     * */
    @Bean(name="shiroCache")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        logger.info("shiro缓存开始记录");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;

    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie(){
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier("rememberMeCookie") SimpleCookie simpleCookie){
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name = "currentUserFilter")
    public CurrentUserFilter getCurrentUserFilter() {
        return new CurrentUserFilter();
    }


    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager,
                                              @Qualifier("currentUserFilter") CurrentUserFilter currentUserFilter) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login.html");
        bean.setSuccessUrl("/index.html");
        Map<String,Filter> filterMap = new HashMap<String,Filter>();
        filterMap.put("currentUserFilter",currentUserFilter);
        bean.setFilters(filterMap);
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login.html", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/doLogin.html", "anon");
        filterChainDefinitionMap.put("/logout.html","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/error*","anon");
        filterChainDefinitionMap.put("/*", "authc,currentUserFilter");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc,currentUserFilter");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc,currentUserFilter");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }


    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    //配置自定义的权限登录器
    @Bean(name="authRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher, @Qualifier("shiroCache") EhCacheManager cacheManager) {
        AuthRealm authRealm = new AuthRealm();
        //告诉realm,使用credentialsMatcher加密算法类来验证密文
        authRealm.setCredentialsMatcher(matcher);
        //缓存
        authRealm.setCacheManager(cacheManager);
        return authRealm;
    }

    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(@Qualifier("shiroCache") EhCacheManager cacheManager) {
        CredentialsMatcher hashedCredentialsMatcher = new CredentialsMatcher(cacheManager);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}