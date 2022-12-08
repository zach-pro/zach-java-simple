package com.zach.modular.nebula.entity.graph;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Descriptin:
 * @ClassName: PropertyBean
 */

@Schema(description ="属性实体")
@Data
@AllArgsConstructor
public class

PropertyBean {

    /**
     * 属性名称
     */
    @Schema(description ="tag/edge属性名称", example = "name",required = true)
    private String propertyName;
    /**
     * 属性类型 (int bool string double .........)
     **/
    @Schema(description ="tag/edge属性类型可选:int bool string double", example = "string",required = true)
    private String propertyType;
    /**
     * 属性描述
     **/
    @Schema(description ="属性描述", example = "名称")
    private String propertyComment;

    /**
     * 是否可为空 (NOT NULL 或者 NULL)
     **/
    @Schema(description ="是否可为空: NOT NULL 或者 NULL", example = "NULL")
    private String isNull;

    /**
     * 默认值
     **/
    @Schema(description ="默认值",example = "NULL")
    private Object defaultValue;

    public Object getDefaultValue() {
        if (!ObjectUtil.isNull(defaultValue)) {
            if (defaultValue instanceof String) {
                return "DEFAULT '" + defaultValue + "'";
            }
            if (defaultValue instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return "DEFAULT date('" + sdf.format(defaultValue) + "')";
            }
            return "DEFAULT " + defaultValue;
        }
        return defaultValue;
    }
}
