package com.sensor.modular.authentication.user.enums;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author by 张益豪
 * @Classname EnabledEnum
 */
@Getter
public enum EnabledEnum {

    ENABLE(1, "启用"),
    DISABLE(2, "禁用");

    @EnumValue
    @JsonValue
    @JSONField
    private final Integer value;
    private final String desc;

    EnabledEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
