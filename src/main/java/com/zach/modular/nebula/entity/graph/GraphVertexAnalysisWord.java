package com.zach.modular.nebula.entity.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="解析文字入参")
public class GraphVertexAnalysisWord {

    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="查询关键字",required = true)
    private String word;
}
