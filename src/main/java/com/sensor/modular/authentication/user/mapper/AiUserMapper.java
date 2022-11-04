package com.sensor.modular.authentication.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.user.entity.AiUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2022-07-28
 */
@Mapper
public interface AiUserMapper extends BaseMapper<AiUser> {
    AiUser selectByName(String userName);
    /**
     * 查询用户的权限列表
     */
    List<AiPermission> selectListByUser(String userId);

    List<AiUser> selectUserList(AiUser user);
}
