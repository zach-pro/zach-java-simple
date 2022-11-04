package com.sensor.common.config.security;

import com.sensor.common.config.security.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author by apple
 * @Classname WebSecurityConfig
 * @Description security配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * 登录成功处理逻辑
     */
    final CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 登录失败处理逻辑
     */
    final CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 权限拒绝处理逻辑
     */
    final CustomizeAccessDeniedHandler accessDeniedHandler;
    /**
     * 匿名用户访问无权限资源时的异常
     */
    final CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 会话失效(账号被挤下线)处理逻辑
     */
    final CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    /**
     * 登出成功处理逻辑
     */
    final CustomizeLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 访问决策管理器
     */
    final CustomizeAccessDecisionManager accessDecisionManager;
    /**
     * 安全数据源
     */
    final CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    /**
     * 实现权限拦截
     */
    private final CustomizeAbstractSecurityInterceptor securityInterceptor;

    public WebSecurityConfig(CustomizeAuthenticationSuccessHandler authenticationSuccessHandler, CustomizeAuthenticationFailureHandler authenticationFailureHandler, CustomizeAccessDeniedHandler accessDeniedHandler, CustomizeAuthenticationEntryPoint authenticationEntryPoint, CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy, CustomizeLogoutSuccessHandler logoutSuccessHandler, CustomizeAccessDecisionManager accessDecisionManager, CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource, CustomizeAbstractSecurityInterceptor securityInterceptor) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.sessionInformationExpiredStrategy = sessionInformationExpiredStrategy;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.accessDecisionManager = accessDecisionManager;
        this.securityMetadataSource = securityMetadataSource;
        this.securityInterceptor = securityInterceptor;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http相关的配置，包括登入登出、异常处理、会话管理等
        http.cors().and().csrf().disable().userDetailsService(userDetailsService());
        http.authorizeRequests().
                 antMatchers("/**").permitAll().
            withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                    //决策管理器
                    o.setAccessDecisionManager(accessDecisionManager);
                    //安全元数据源
                    o.setSecurityMetadataSource(securityMetadataSource);
                    return o;
                }
            }).
            //登出
            and().logout().
                //允许所有用户
                permitAll().
                //登出成功处理逻辑
                logoutSuccessHandler(logoutSuccessHandler).
                //登出之后删除cookie
                deleteCookies("JSESSIONID").
            //登入
            and().formLogin().
                //允许所有用户
                permitAll().
                //登录成功处理逻辑
                successHandler(authenticationSuccessHandler).
                //登录失败处理逻辑
                failureHandler(authenticationFailureHandler).
            //异常处理(权限拒绝、登录失效等)
            and().exceptionHandling().
                //权限拒绝处理逻辑
                accessDeniedHandler(accessDeniedHandler).
                //匿名用户访问无权限资源时的异常处理
                authenticationEntryPoint(authenticationEntryPoint).
            //会话管理
            and().sessionManagement().
                //同一账号同时登录最大用户数
                maximumSessions(1).
                // 禁止第2次登陆
                //maxSessionsPreventsLogin(true).
                sessionRegistry(sessionRegistry()).
                //会话失效(账号被挤下线)处理逻辑
                expiredSessionStrategy(sessionInformationExpiredStrategy);
        // url请求拦截
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
        return http.build();
    }
}