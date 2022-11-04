package com.sensor.modular.authentication.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sensor.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiMenu extends BaseEntity<AiMenu> {

    @Schema(description  = "父类id")
    private String parentId;

    @TableField(exist = false)
    @Schema(description  = "父类菜单名称")
    private String parentName;

    @Schema(description  = "菜单地址")
    private String menuHref;

    @Schema(description  = "菜单名称")
    private String menuName;

    @Schema(description  = "排序")
    private Integer sort;

    @Schema(description  = "菜单类型: 1父类菜单 2子类菜单")
    private Integer type;

}
