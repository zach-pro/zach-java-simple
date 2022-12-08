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
@Schema(description ="创建索引入参")
public class GraphCreateIndex {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense",required = true)
    private String space;

    @Schema(description ="创建类型: tag/edge",example = "tag",required = true)
    private String type;

    @Schema(description ="索引名称",example = "name_index",required = true)
    private String indexName;

    @Schema(description ="tag/edge名称",example = "t1",required = true)
    private String tagEdgeName;

    @Schema(description ="索引描述描述",example = "备注")
    private String comment;

    @Schema(description ="属性集合")
    private List<AttributeBean> attributeBeanList;
}
