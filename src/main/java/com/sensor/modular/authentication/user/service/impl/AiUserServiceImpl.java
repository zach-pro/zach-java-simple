package com.sensor.modular.authentication.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.user.entity.AiUser;
import com.sensor.modular.authentication.user.mapper.AiUserMapper;
import com.sensor.modular.authentication.user.service.AiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zach
 * @since 2022-11-04
 */
@Service
public class AiUserServiceImpl extends ServiceImpl<AiUserMapper, AiUser> implements AiUserService {
    @Autowired
    AiUserMapper aiUserMapper;

    @Override
    public AiUser loadUserByName(String userName) {
        return aiUserMapper.selectByName(userName);
    }

    @Override
    public List<AiPermission> selectListByUser(String userId) {
        return aiUserMapper.selectListByUser(userId);
    }

    @Override
    public List<AiUser> getUserList(AiUser graphUser) {
        return aiUserMapper.selectUserList(graphUser);
    }
}
