package com.zach.modular.nebula.entity.graph;


import com.zach.common.vo.PageBeanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="查询属性分页查询入参")
public class GraphPageAttribute extends PageBeanDto {

    @Schema(description ="空间名称",example = "sense",required = true)
    private String space;

    @Schema(description ="attribute可选值:tags标签/edges边类型",example = "tags",required = true)
    private String attribute;

}
