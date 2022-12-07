package com.sensor.modular.authentication.user_role_relation.entity;


import com.sensor.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联关系表
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiUserRoleRelation extends BaseEntity<AiUserRoleRelation> {
    @Schema(description  = "用户Id")
    private String userId;
    @Schema(description  = "角色Id")
    private String roleId;

}
