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
@Schema(description ="关系详情查询入参")
public class GraphShowEdge {

    /**
     * 项目ID
     **/
    @Schema(description ="项目ID",example = "sense")
    private String projectId;

    /**
     * attributeName:  tag 名称/edge 名称
     **/
    @Schema(description ="属性名称")
    private String attributeName;

}
