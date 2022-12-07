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
@Schema(description ="插入边入参")
public class GraphInsertEdge {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="边类型edge名称", example = "e1", required = true)
    private String edgeName;

    @Schema(description ="边类型edge属性集合", required = false)
    private List<String> edgeList;

    @Schema(description ="点的起始VID", example = "11", required = true)
    private String srcVid;

    @Schema(description ="点的目的VID", example = "12", required = true)
    private String dstVid;

    @Schema(description ="边edge的属性值集合", required = false)
    private List<Object> edgeValueList;
}
