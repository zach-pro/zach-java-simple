package com.sensor.common.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: zyh
 * @Description: schema设计常用集合枚举
 */
public enum SchemaUsedEnum {
    /**
     * 默认索引长度
     */
    STRING_INDEX_DEF_SIZE(100, "默认索引长度"),
    /**
     * 数据库连接类型
     */
    STRING(1, "string"),
    INT(2, "int"),
    DOUBLE(3, "double"),
    BOOLEAN(4, "bool"),
    DATATYPE(5, "date"),
    /**
     * 允许为空值
     */
    ALLOW_NOT_NULL(1, "NOT NULL"),
    ALLOW_NULL(2, "NULL"),
    /**
     * 是否索引
     */
    INDEX_YES(1, "是"),
    INDEX_NO(2, "不是"),
    /**
     * nebula类型
     */
    NEBULA_TAG(1, "TAG"),
    NEBULA_EDGE(2, "EDGE"),
    /**
     * 数据表字段类型
     */
    DATA_VARIABLE(1, "变量"),
    DATA_FINAL(2, "常量"),
    /**
     * 查询类型
     */
    TYPE_TABLE(1, "所有表"),
    TYPE_VIEW(2, "所有视图"),
    TYPE_TABLE_VIEW(3, "所有表和视图"),
    /**
     * 查询类型
     */
    DB_VIEW(1, "VIEW"),
    DB_TABLE(2, "BASE TABLE"),
    /**
     * 映射状态
     */
    MAPPING_NO(1, "未映射"),
    MAPPING_YES(2, "已映射"),
    /**
     * 映射状态
     */
    HANDLE_NO(1, "未处理"),
    HANDLE_YES(2, "已处理"),
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
     * 实体类型
     */
    ATTR_TYPE_TEXT(1,"文本值"),
    ATTR_TYPE_IMAGE(2,"图像"),
    ATTR_TYPE_WORD(3,"文档"),
    ATTR_TYPE_URL(4,"网址"),
    /**
     * 任务执行状态
     */
    TASK_STATUS_SUCCESS(1,"成功"),
    TASK_STATUS_FAIL(2,"失败"),
    TASK_STATUS_RUNNING(3,"进行中"),
    /**
     * 项目步骤
     */
    PROJECT_STEP_INIT(1,"新建项目"),
    PROJECT_STEP_SCHEMA(2,"schema设计"),
    PROJECT_STEP_MAPPING_1(3,"关系映射第一步"),
    PROJECT_STEP_MAPPING_2(4,"关系映射第二步"),
    PROJECT_STEP_MAPPING_3(5,"关系映射第三步"),
    PROJECT_STEP_MAPPING_4(6,"关系映射第四步"),
    PROJECT_STEP_NT_GENERATE(7,"生成任务"),
    PROJECT_STEP_GRAPH_GENERATE(8,"图谱构建"),
    /**
     * 文本抽取状态
     */
    EXTRACTION_EXTRACTING(1,"执行中"),
    EXTRACTION_SUCCESS(2,"成功"),
    EXTRACTION_FAIL(3,"失败"),
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
    FILE_JPEG(5,"image/jpeg"),
    /**
     * 文件类型
     */
    SCHEMA_BASE(1,"base"),
    SCHEMA_LINE(2,"line"),
    /**
     * 实体节点标识
     */
    ENTITY_IMAGE(1,"自定义图片"),
    ENTITY_ICON(2,"内置icon"),
    /**
     * 实体节点主键类型
     */
    P_KEY_ENTITY(1,"实体"),
    P_KEY_ATTR(2,"属性"),
    /**
     * 模型状态
     */
    PROJECT_MODEL_SUCCESS(1,"正常"),
    PROJECT_MODEL_ABNORMAL(2,"异常"),
    PROJECT_MODEL_UNKNOWN(3,"未知");

    private final Integer index;
    private final String describe;

    SchemaUsedEnum(Integer index, String describe) {
        this.index = index;
        this.describe = describe;
    }

    public static String selDataType(Integer dataType){
        if (STRING.getIndex().equals(dataType)) {
            return STRING.getDescribe();
        }else
        if (INT.getIndex().equals(dataType)) {
            return INT.getDescribe();
        }else
        if (DOUBLE.getIndex().equals(dataType)) {
            return DOUBLE.getDescribe();
        }else
        if (BOOLEAN.getIndex().equals(dataType)) {
            return BOOLEAN.getDescribe();
        }else
        if (DATATYPE.getIndex().equals(dataType)) {
            return DATATYPE.getDescribe();
        }
        return STRING.getDescribe();
    }

    public static Object selDefaultStrByType(Integer dataType,String defaultStr){
        if (defaultStr == null || defaultStr.isEmpty()) {
            return null;
        }
        if (STRING.getIndex().equals(dataType)) {
            return defaultStr;
        }else
        if (INT.getIndex().equals(dataType)) {
            return Integer.valueOf(defaultStr);
        }else
        if (DOUBLE.getIndex().equals(dataType)) {
            return Double.valueOf(defaultStr);
        }else
        if (BOOLEAN.getIndex().equals(dataType)) {
            return Boolean.valueOf(defaultStr);
        }else
        if (DATATYPE.getIndex().equals(dataType)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(defaultStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Integer getIndex() {
        return index;
    }

    public String getDescribe() {
        return describe;
    }
}
