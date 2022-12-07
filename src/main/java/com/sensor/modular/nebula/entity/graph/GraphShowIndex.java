package com.sensor.modular.nebula.entity.graph;


import com.sensor.common.vo.PageBeanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="查询索引入参")
public class GraphShowIndex extends PageBeanDto {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称",example = "sense",required = true)
    private String space;
    /**
     * attribute:  tag/edge
     **/
    @Schema(description ="属性可选: tag/edge/fulltext",example = "tag",required = true)
    private String attribute;

}
