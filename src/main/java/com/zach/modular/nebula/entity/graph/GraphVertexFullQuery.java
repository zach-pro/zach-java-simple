package com.zach.modular.nebula.entity.graph;

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
@Schema(description ="全文检索")
public class GraphVertexFullQuery {

    @Schema(description ="空间名称", example = "movies", required = true)
    private String space;

    @Schema(description ="查询关键字",required = true)
    private String word;

    @Schema(description ="标签集合",required = false)
    private List<String> tagList;

    @Schema(description ="查询最大条数",required = true)
    private Integer resultSize;
}
