package com.sensor.modular.authentication.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.user.entity.AiUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zach
 * 
 */
public interface AiUserService extends IService<AiUser> {
    /**
     * 基于用户名查询用户信息
     */
    AiUser loadUserByName(String username);

    List<AiPermission> selectListByUser(String userId);

    List<AiUser> getUserList(AiUser graphUser);
}
