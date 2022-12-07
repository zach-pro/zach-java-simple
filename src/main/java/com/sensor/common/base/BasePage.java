package com.sensor.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author by 张益豪
 * @Classname BasePage
 * @Description TODO
 * @Date 2022/11/9 11:47
 */
@Data
@AllArgsConstructor
public class BasePage {

    @Schema(description = "当前页码")
    private Integer currentPage;

    @Schema(description = "分页数")
    private Integer pageSize;
}
