package com.zach.modular.elastic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Classname EsQueryDTO
 */
@Data
public class EsQueryDTO {
    @Schema(description = "关键字属性")
    private String field;
    @Schema(description = "关键字")
    private String word;
    @Schema(description = "起始行")
    private Integer from;
    @Schema(description = "页数")
    private Integer size;
    @Schema(description = "排序属性")
    private String sort;

    public Integer getSize() {
        return size==0?30:size;
    }
}
