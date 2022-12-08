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
@Schema(description ="修改点入参")
public class GraphUpdateVertex {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="标签tag名称", example = "t1", required = true)
    private String tagName;

    @Schema(description ="标签tag属性集合", required = true)
    private List<String> tagList;
    /**
     * point的key
     **/
    @Schema(description ="点的VID", example = "11", required = true)
    private Object pointKey;

    @Schema(description ="标签tag的属性值集合", required = true)
    private List<Object> tagValueList;
}
