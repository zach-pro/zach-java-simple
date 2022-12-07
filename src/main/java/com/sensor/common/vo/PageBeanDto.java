package com.sensor.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author apple
 */
@Schema(description = "分页参数入参")
@Data
public class PageBeanDto {

    @Schema(description = "页码:从1开始",example = "1",required = false)
    private Integer pageNum = 1;

    @Schema(description = "分页条数",example = "10",required = false)
    private Integer pageSize = 10;
}
