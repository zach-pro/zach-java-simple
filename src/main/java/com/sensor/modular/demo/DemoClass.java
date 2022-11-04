package com.sensor.modular.demo;

import com.sensor.modular.demo.constant.TypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author apple
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "Demo类")
public class DemoClass {
    @Schema(description = "目录")
    private Integer dirs;
    @Schema(description = "名称", minLength = 1, maxLength = 5, required = true)
    private String name;
    @Schema(description = "类型: 1整数 2小数", allowableValues = {"1", "2"}, required = true)
    private TypeEnum type;

}
