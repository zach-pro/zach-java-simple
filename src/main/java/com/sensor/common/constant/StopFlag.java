package com.sensor.common.constant;

/**
 * @author by 张益豪
 * @Classname StopFlag
 * @Description 用户状态
 * @Date 2022/7/29 11:10
 */
public enum StopFlag {
    /**
     * ENABLE
     */
    ENABLE("1", "启用"),
    DISABLE("2", "禁用");

    private String errCode;
    private String desc;

    StopFlag(String errCode, String desc) {
        this.errCode = errCode;
        this.desc = desc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getDesc() {
        return desc;
    }
}
