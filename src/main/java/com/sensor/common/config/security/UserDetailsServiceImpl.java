package com.sensor.common.config.security;

import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.user.entity.AiUser;
import com.sensor.modular.authentication.user.service.impl.AiUserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by apple
 * @Classname UserDetailsServiceImpl
 * @Description 校验登录用户
 * @Date 2022/7/29 14:28
 */
@Component
@NoArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {
    AiUserServiceImpl aiUserService;
    @Autowired
    public void setAiUserService(AiUserServiceImpl aiUserService) {
        this.aiUserService = aiUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户
        AiUser aiUser = aiUserService.loadUserByName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<AiPermission> aiPermissions = aiUserService.selectListByUser(aiUser.getId());
        // 声明用户授权
        aiPermissions.forEach(aiPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(aiPermission.getPermissionCode());
            grantedAuthorities.add(grantedAuthority);
        });
        return new User(aiUser.getAccount(), aiUser.getPassword(), aiUser.selEnabled(),  true,true, aiUser.selAccountNotLocked(), grantedAuthorities);
    }
}