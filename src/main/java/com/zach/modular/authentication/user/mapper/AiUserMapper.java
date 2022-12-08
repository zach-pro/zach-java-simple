package com.zach.modular.authentication.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zach.modular.authentication.permission.entity.AiPermission;
import com.zach.modular.authentication.user.entity.AiUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author apple
 * 
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
