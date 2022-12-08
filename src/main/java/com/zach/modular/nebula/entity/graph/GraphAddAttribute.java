package com.zach.modular.nebula.entity.graph;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="修改属性实体")
public class GraphAddAttribute {


    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;

    @Schema(description ="attribute可选值: tag标签/edge边类型",example = "tag",required = true)
    private String attribute;

    @Schema(description ="属性名称", example = "t1",required = true)
    private String attributeName;

    @Schema(description ="tag/edge的属性名称", example = "p5")
    private String propertyName;

    @Schema(description ="属性类型,add 必传该类型 可选值: int bool string double .........", example = "string",required = false)
    private String propertyType;

    @Schema(description ="是否可为空: NOT NULL 或者 NULL", example = "NULL")
    private String isNull;

    @Schema(description ="默认值")
    private Object defaultValue;

    @Schema(description ="描述")
    private String common;

    public Object getDefaultValue() {
        if (!ObjectUtil.isNull(defaultValue)) {
            if (defaultValue instanceof String) {
                return "DEFAULT '" + defaultValue + "'";
            }
            return "DEFAULT " + defaultValue;
        }
        return defaultValue;
    }

    public String getCommon() {
        if (StrUtil.isNotBlank(common)) {
            return  "COMMENT '" + common + "'";
        }
        return common;
    }
}
