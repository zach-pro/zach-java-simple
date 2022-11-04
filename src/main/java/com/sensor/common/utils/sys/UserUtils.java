package com.sensor.common.utils.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sensor.modular.authentication.user.entity.AiUser;
import com.sensor.modular.authentication.user.service.impl.AiUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户工具类
 *
 * @author apple
 */
@Component
public class UserUtils {

    @Autowired
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        UserUtils.sessionRegistry = sessionRegistry;
    }
    @Autowired
    public void setAiUserService(AiUserServiceImpl aiUserService) {
        UserUtils.aiUserService = aiUserService;
    }
    private static SessionRegistry sessionRegistry;
    private static AiUserServiceImpl aiUserService;

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static AiUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            AiUser aiUser = aiUserService.loadUserByName(currentUserName);
            if (aiUser != null) {
                return aiUser;
            }
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new AiUser();
    }

    /**
     * 强制下线用户
     * @param username 账号
     * @return
     */
    public static Boolean removeUser(String username){
        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof User) {
                final User loggedUser = (User) principal;
                if (username.equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo =
                            // false代表不包含过期session
                            sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && !sessionsInfo.isEmpty()) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前账户已存在
     * @param account
     * @return true: 已存在 false: 不存在
     */
    public static boolean existenceAccount(String account){
        LambdaQueryWrapper<AiUser> qw = new LambdaQueryWrapper<>();
        qw.eq(AiUser::getAccount,account);
        AiUser one = aiUserService.getOne(qw);
        if (one != null && one.getId() != null) {
            return true;
        }
        return false;
    }
}
