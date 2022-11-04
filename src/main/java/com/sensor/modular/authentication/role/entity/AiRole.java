package com.sensor.modular.authentication.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sensor.common.base.BaseEntity;
import com.sensor.modular.authentication.menu.entity.AiMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author apple
 * @since 2022-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRole extends BaseEntity<AiRole> {

    @Schema(description  = "角色名称")
    private String roleName;

    @Schema(description  = "角色描述")
    private String roleDescribe;

    @Schema(description  = "菜单ids")
    @TableField(exist = false)
    private List<String> menuIdList;

    @Schema(description  = "菜单集合")
    @TableField(exist = false)
    private List<AiMenu> menuList;
}
