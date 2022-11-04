package com.sensor.common.constant;

/**
 * @Author: apple
 * @Description: 数据库类型
 */
public enum DbTypeEnum {
    /**
     * 数据库连接类型
     */
    DM(1, "达梦"),
    MYSQL(2, "Mysql"),
    ORACLE(3, "Oracle"),
    HIVE(4, "hive");
    private Integer dbName;
    private String Describe;

    public Integer getDbName() {
        return dbName;
    }

    public String getDescribe() {
        return Describe;
    }

    DbTypeEnum(Integer dbName, String describe) {
        this.dbName = dbName;
        Describe = describe;
    }
}
