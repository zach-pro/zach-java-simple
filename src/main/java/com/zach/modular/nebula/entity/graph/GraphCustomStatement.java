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
@Schema(description ="自定义查询")
public class GraphCustomStatement {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="自定义语句", required = true)
    private String customStatement;
}
