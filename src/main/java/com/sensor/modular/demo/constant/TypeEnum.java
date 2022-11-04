package com.sensor.modular.demo.constant;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: apple
 * @Description: 返回码定义
 * 规定:
 * #1表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 */
@Getter
@AllArgsConstructor
public enum TypeEnum implements IEnum<TypeEnum> {
    /* 成功 */
    INTEGER(1, "整数"),
    DECIMAL(2, "小数");

    private final Integer code;
    private final String message;

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (TypeEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }

    @Override
    public TypeEnum getValue() {
        return null;
    }
}
