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
@Schema(description ="路径查询")
public class GraphPathQuery {

    @Schema(description ="空间名称", example = "movies", required = true)
    private String space;

    @Schema(description ="实体节点Id",required = true)
    private String srcId;

    @Schema(description ="目标节点Id",required = true)
    private String dstId;

    @Schema(description ="查询类型: SHORTEST：查找最短路径,ALL：查找所有路径,NOLOOP：查找非循环路径",required = true)
    private String pathMode;

    @Schema(description ="查询方向: REVERSELY反向 | BIDIRECT双向 | 空 正向", example = "", required = true)
    private String direction;

    @Schema(description ="关系集合：默认全部｜ edge的列表",required = false)
    private List<String> edgeList;

    @Schema(description ="最大的条数，默认设置5",required = true)
    private Integer maxJump;

    @Schema(description ="最大结果条数",required = true)
    private Integer maxResultSize;
}
