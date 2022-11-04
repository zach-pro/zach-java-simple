package com.sensor.common.constant;

/**
 * @author by 张益豪
 * @Classname LockFlag
 * @Description TODO
 * @Date 2022/7/29 11:12
 */
public enum LockFlag {
    /**
     * ENABLE
     */
    UN_LOCKED("1", "否: 未锁定"),
    LOCKED("2", "是: 锁定");

    private String errCode;
    private String desc;

    LockFlag(String errCode, String desc) {
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