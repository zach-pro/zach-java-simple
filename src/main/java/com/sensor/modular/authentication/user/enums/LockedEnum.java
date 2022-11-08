package com.sensor.modular.authentication.user.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sensor.common.base.SwaggerDisplayEnum;
import lombok.Getter;

/**
 * @author by 张益豪
 * @Classname LockedEnum
 * @Description 账户锁定
 */
@Getter
@SwaggerDisplayEnum(index = "value", name = "desc")
public enum LockedEnum implements IEnum<Integer> {
    UN_LOCKED(1, "否: 未锁定"),
    LOCKED(2, "是: 锁定");

    @JsonValue
    private final Integer value;
    private final String desc;

    LockedEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}


