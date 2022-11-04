package com.sensor.modular.authentication.role_permission_relation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.authentication.role_permission_relation.entity.AiRolePermissionRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色-权限关联关系表 Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2022-07-29
 */
@Mapper
public interface AiRolePermissionRelationMapper extends BaseMapper<AiRolePermissionRelation> {

}
