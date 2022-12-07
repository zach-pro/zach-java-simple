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
@Schema(description ="修改边入参")
public class GraphUpdateEdge {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="边类型edge名称", example = "e1", required = true)
    private String edgeName;

    @Schema(description ="边类型edge属性集合", required = false)
    private List<String> edgeList;

    @Schema(description ="点的起始VID", required = true)
    private Object srcVid;

    @Schema(description ="点的目的VID", required = true)
    private Object dstVid;

    @Schema(description ="边edge的属性值集合", required = false)
    private List<Object> edgeValueList;
}









