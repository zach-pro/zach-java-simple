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
@Schema(description ="查询相关属性入参")
public class GraphShowAttribute {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense",required = false)
    private String space;
    /**
     * attributes:  spaces/tags/edges
     **/
    @Schema(description ="attribute可选值:spaces空间/tags标签/edges边类型",example = "spaces",required = true)
    private String attribute;

}
