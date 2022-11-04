package com.sensor.modular.nebula.entity.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="删除属性实体")
public class GraphDropAttribute {


    @Schema(description ="空间名称", example = "sense1",required = true)
    private String space;

    @Schema(description ="属性名称", example = "sense1",required = true)
    private String attributeName;

    @Schema(description ="attribute可选值:space空间/tag标签/edge边类型",example = "space",required = true)
    private String attribute;
}
