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
@Schema(description ="删除边入参")
public class GraphDeleteEdge {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="边类型edge名称", example = "e1", required = true)
    private String edgeName;

    @Schema(description ="点的起始VID", example = "11", required = true)
    private Object srcVid;

    @Schema(description ="点的目的VID", example = "12", required = true)
    private Object dstVid;

}
