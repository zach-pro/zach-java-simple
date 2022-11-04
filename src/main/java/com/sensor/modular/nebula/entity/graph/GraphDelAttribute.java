package com.sensor.modular.nebula.entity.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="删除属性实体")
public class GraphDelAttribute {


    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;

    @Schema(description ="attribute可选值: tag标签/edge边类型",example = "tag",required = true)
    private String attribute;

    @Schema(description ="属性名称", example = "t1",required = true)
    private String attributeName;

    @Schema(description ="tag/edge的属性名称 支持批量")
    private List<String> propertyNameList;
}
