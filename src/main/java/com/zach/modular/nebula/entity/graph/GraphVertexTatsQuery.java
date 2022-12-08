package com.zach.modular.nebula.entity.graph;


import com.zach.common.vo.PageBeanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="根据tag标签查询点入参实体")
public class GraphVertexTatsQuery extends PageBeanDto {

    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="标签", required = false)
    private String tag;

    @Schema(description ="点id", required = false)
    private Object pointKey;
}
