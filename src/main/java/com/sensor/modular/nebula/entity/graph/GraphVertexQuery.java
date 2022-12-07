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
@Schema(description ="查询全文检索")
public class GraphVertexQuery {

    @Schema(description ="空间名称", example = "movies", required = true)
    private String space;

    @Schema(description ="tag类型名称",required = false)
    private String tagName;

    @Schema(description ="属性集合",required = false)
    private List<String> attrList;

    @Schema(description ="属性值集合",required = false)
    private List<Object> attrValueList;

    @Schema(description ="edge类型名称集合",required = false)
    private List<String> edgeList;

    @Schema(description ="查询条数",required = false)
    private Integer resultSize;
}
