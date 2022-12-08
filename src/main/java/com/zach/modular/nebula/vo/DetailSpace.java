package com.zach.modular.nebula.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Descriptin: 图空间详情
 * @ClassName: DetailSpace
 */
@Schema(description ="图空间详情实体")
@Data
public class DetailSpace {

    @Schema(description = "空间名称")
    private String space;

    @Schema(description = "标签个数")
    private Integer tagsNum;

    @Schema(description = "边类型个数")
    private Integer edgesNum;

    @Schema(description = "空间备注")
    private String spaceComment;
}
