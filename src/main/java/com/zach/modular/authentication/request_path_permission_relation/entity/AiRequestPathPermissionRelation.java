package com.zach.modular.authentication.request_path_permission_relation.entity;


import com.zach.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路径权限关联表
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRequestPathPermissionRelation extends BaseEntity<AiRequestPathPermissionRelation> {

    @Schema(description  = "请求路径id")
    private String urlId;

    @Schema(description  = "权限id")
    private String permissionId;

}
