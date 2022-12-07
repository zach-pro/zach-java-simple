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
@Schema(description ="归因分析查询入参")
public class GraphVertexAnalysisResult {

    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="查询关键字集合",required = true)
    private List<String> wordList;
}
