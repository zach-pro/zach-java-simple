package com.zach.modular.authentication.role_permission_relation.entity;

import com.zach.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色-权限关联关系表
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRolePermissionRelation extends BaseEntity<AiRolePermissionRelation> {

    @Schema(description  = "角色Id")
    private String roleId;

    @Schema(description  = "权限Id")
    private String permissionId;
}
