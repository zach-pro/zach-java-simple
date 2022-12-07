package com.sensor.modular.nebula.entity.graph;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="根据tag标签查询点入参实体")
public class GraphVertexTatAttributeQuery {

    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="标签", example = "t1", required = true)
    private String tag;

    @Schema(description ="执行条件", required = true)
    private String condition;

    @Schema(description ="结果条数", example = "100", required = true)
    @Max(Integer.MAX_VALUE)
    @Min(1)
    private Integer resultSize = Integer.MAX_VALUE;


    public String getCondition() {
        if (StrUtil.isNotBlank(condition)) {
            return " where " + condition;
        }
        return "";
    }
}
