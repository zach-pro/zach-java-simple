package com.sensor.modular.nebula.entity.graph;


import com.sensor.common.vo.PageBeanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="查询边分页入参")
public class GraphPageEdge extends PageBeanDto {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="边类型edge", required = false)
    private String edge;
}









