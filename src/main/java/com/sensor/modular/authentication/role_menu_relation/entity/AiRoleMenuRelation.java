package com.sensor.modular.authentication.role_menu_relation.entity;

import com.sensor.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author apple
 * @since 2022-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRoleMenuRelation extends BaseEntity<AiRoleMenuRelation> {

    @Schema(description  = "角色id")
    private String roleId;

    @Schema(description  = "菜单id")
    private String menuId;
}
