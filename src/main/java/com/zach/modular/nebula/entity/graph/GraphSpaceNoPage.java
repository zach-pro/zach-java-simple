package com.zach.modular.nebula.entity.graph;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="无page入参实体")
public class GraphSpaceNoPage{

    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;
}
