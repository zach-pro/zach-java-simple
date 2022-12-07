package com.sensor.common.config.mybatis;

import com.baomidou.mybatisplus.annotation.IEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

/**
 * @author apple
 */
public class Str2EnumConverter<T extends Enum<?> & IEnum<Integer>> implements Converter<String, T> {
    Class<T> cls;

    Str2EnumConverter(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public T convert(@NotNull String source) {
        T[] enumConstants = cls.getEnumConstants();
        Integer sourceVal = Integer.valueOf(source);
        for (T enumInstance : enumConstants) {
            if (sourceVal.equals(enumInstance.getValue())){
                return enumInstance;
            }
        }
        return null;
    }
}
