package com.sensor.modular.nebula.entity.graph;


import com.sensor.common.vo.PageBeanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="通用入参实体")
public class GraphSpace extends PageBeanDto {

    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;
}
