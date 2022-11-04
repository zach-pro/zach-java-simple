package com.sensor.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by 张益豪
 * @Classname NebulaConstant
 * @Description nebula常用字符串
 * @Date 2022/8/17 17:14
 */
public class NebulaConstant {
    public static final String USE = "USE ";
    public static final String SEMICOLON = "; ";
    public static final String ERROR_CODE = "-1";
    public static final String SUFFIX_INDEX = "_index";

    @Getter
    @AllArgsConstructor
    public enum NebulaJson{
        ERRORS("errors"),
        CODE("code"),
        MESSAGE("message"),
        RESULTS("results"),
        COLUMNS("columns"),
        DATA("data"),
        ROW("row");
        private String key;
    }
}