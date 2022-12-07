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
@Schema(description ="关系节点查询")
public class GraphEdgeQuery {

    @Schema(description ="空间名称", example = "movies", required = true)
    private String space;

    @Schema(description ="Vertex主键",required = false)
    private String vertexId;

    @Schema(description ="edge类型名称集合",required = false)
    private List<String> edgeList;
}
