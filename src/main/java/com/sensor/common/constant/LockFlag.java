package com.sensor.common.constant;

/**
 * @author by apple
 * @Classname LockFlag
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