package com.emas.common.constant;

/**
 * @Author: apple
 * @Description: schema设计常用集合枚举
 */
public enum SchemaUsedEnum {

    /**
     * nebula类型
     */
    NEBULA_TAG(1, "TAG"),
    NEBULA_EDGE(2, "EDGE"),

    /**
     * 菜单类型
     */
    MENU_PARENT(1, "父级菜单"),
    MENU_CHILDREN(2, "子级菜单"),
    /**
     * 线段形状
     */
    SHAPE_SMOOTH(1,"平滑线"),
    SHAPE_POLY(2,"多段线"),
    /**
     * 定时任务方式
     */
    TASK_MODE_CYCLE(1,"按周期执行"),
    TASK_MODE_DAY(2,"每天定时执行"),
    TASK_MODE_CRON(3,"按定时表达式执行"),
    /**
     * 定时任务-是否启动
     */
    TASK_ENABLED(1,"启动"),
    TASK_DISABLE(2,"停止"),
    /**
     * 定时任务-是否启动
     */
    TASK_JOB_NAME(1,"jobName"),
    TASK_JOB_GROUP_NAME(2,"jobGroupName"),
    TASK_JOB_TRIGGER_NAME(3,"triggerName"),
    TASK_JOB_TRIGGER_GROUP_NAME(4,"triggerGroupName"),
    /**
     * 任务类型
     */
    TASK_TYPE_AUTOMATIC(1,"自动"),
    TASK_TYPE_MANUAL(2,"手动"),

    /**
     * 任务执行状态
     */
    TASK_STATUS_SUCCESS(1,"成功"),
    TASK_STATUS_FAIL(2,"失败"),
    TASK_STATUS_RUNNING(3,"进行中"),

    /**
     * 文件类型
     */
    FILE_TYPE_IMG(1,"图标"),
    FILE_TYPE_DATA(2,"数据"),

    /**
     * 文件类型
     */
    FILE_PNG(1,"image/png"),
    FILE_JPG(2,"image/jpg"),
    FILE_CSV(3,"text/csv"),
    FILE_TXT(4,"text/plain"),
    FILE_JPEG(5,"image/jpeg");

    private final Integer index;
    private final String describe;

    SchemaUsedEnum(Integer index, String describe) {
        this.index = index;
        this.describe = describe;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDescribe() {
        return describe;
    }
}
