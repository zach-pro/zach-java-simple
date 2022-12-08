package com.zach.modular.authentication.permission.entity;

import com.zach.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiPermission extends BaseEntity<AiPermission> {

    @Schema(description  = "权限编码")
    private String permissionCode;

    @Schema(description  = "权限名")
    private String permissionName;

}
