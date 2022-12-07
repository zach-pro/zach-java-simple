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
@Schema(description ="创建tag edge入参")
public class GraphCreateTagEdge {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense")
    private String space;

    @Schema(description ="创建类型: tag/edge",example = "tag")
    private String type;

    @Schema(description ="tag/edge名称",example = "demo")
    private String tagEdgeName;

    @Schema(description ="tag/edge描述",example = "备注")
    private String tagEdgeComment;

    @Schema(description ="属性集合")
    private List<PropertyBean> propertyList;

    @Schema(description ="颜色")
    private String color;
}
