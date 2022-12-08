package com.zach.common.config.mybatis;

import com.baomidou.mybatisplus.annotation.IEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author apple
 */
public class Str2EnumConverterFactory<T extends Enum<?> & IEnum<Integer>> implements ConverterFactory<String, T> {

    @NotNull
    @Override
    public <T1 extends T> Converter<String, T1> getConverter(@NotNull Class<T1> targetType) {
        return new Str2EnumConverter<>(targetType);
    }
}
