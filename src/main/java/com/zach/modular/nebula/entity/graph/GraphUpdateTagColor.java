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
@Schema(description ="修改tag颜色入参")
public class GraphUpdateTagColor {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense")
    private String space;

    @Schema(description ="类型: tag/edge",example = "tag")
    private String type;

    @Schema(description ="tag/edge名称",example = "demo")
    private String tagEdgeName;

    @Schema(description ="颜色")
    private String color;
}
