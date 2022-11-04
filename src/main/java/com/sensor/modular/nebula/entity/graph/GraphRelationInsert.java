package com.sensor.modular.nebula.entity.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 关系绑定入参
 *
 * @ClassName: GraphRelationInsert
 */
@Data
public class GraphRelationInsert {

    @Schema(description ="空间名称", example = "movies", required = true)
    private String space;

    @Schema(description ="开始标签", required = true)
    private String tagStart;

    @Schema(description ="边类型", required = true)
    private String edge;

    @Schema(description ="结束标签", required = true)
    private String tagEnd;
}
