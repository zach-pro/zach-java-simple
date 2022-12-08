package com.zach.common.config.security.handler;

import com.zach.modular.authentication.permission.entity.AiPermission;
import com.zach.modular.authentication.permission.service.impl.AiPermissionServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: apple
 * @Description: 元数据源
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    public static final String MARK = "?";
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    final AiPermissionServiceImpl aiPermissionService;

    public CustomizeFilterInvocationSecurityMetadataSource(AiPermissionServiceImpl aiPermissionService) {
        this.aiPermissionService = aiPermissionService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if (requestUrl.contains(MARK)) {
            requestUrl = requestUrl.substring(0,requestUrl.indexOf(MARK));
        }
        //查询具体某个接口的权限
        List<AiPermission> permissionList =  aiPermissionService.selectListByPath(requestUrl);
        if(permissionList == null || permissionList.isEmpty()){
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return Collections.emptyList();
        }
        String[] attributes = new String[permissionList.size()];
        for(int i = 0;i<permissionList.size();i++){
            attributes[i] = permissionList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}