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
@Schema(description ="修改空间备注入参实体")
public class GraphUpdateSpace {

    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;

    @Schema(description ="空间中文名称", example = "空间中文名称",required = true)
    private String spaceChineseName;

    @Schema(description ="空间备注", example = "备注信息",required = true)
    private String spaceComment;
}
