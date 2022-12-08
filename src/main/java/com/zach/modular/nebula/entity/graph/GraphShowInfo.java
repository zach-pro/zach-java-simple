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
@Schema(description ="属性详情查询入参")
public class GraphShowInfo {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense")
    private String space;

    /**
     * attribute:  tag /edge
     **/
    @Schema(description ="属性类型:tag/edge",example = "tag")
    private String attribute;

    /**
     * attributeName:  tag 名称/edge 名称
     **/
    @Schema(description ="属性名称")
    private String attributeName;

}
