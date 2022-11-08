package com.sensor.modular.authentication.user.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sensor.common.base.SwaggerDisplayEnum;
import lombok.Getter;

/**
 * @author by 张益豪
 * @Classname EnabledEnum
 */
@Getter
@SwaggerDisplayEnum(index = "value", name = "desc")
public enum EnabledEnum implements IEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(2, "禁用");

    @JsonValue
    private final Integer value;
    private final String desc;

    EnabledEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
