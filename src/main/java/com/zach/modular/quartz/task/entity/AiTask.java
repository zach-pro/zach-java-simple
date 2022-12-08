package com.zach.modular.quartz.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zach.common.base.BaseEntity;
import com.zach.common.constant.SchemaUsedEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiTask extends BaseEntity<AiTask> {

    @Schema(description  = "任务名称")
    private String taskName;

    @Schema(description  = "定时任务方式")
    private Integer taskMode;

    @Schema(description  = "小时 周期模式取值范围[0-23]之间")
    private Integer timeHour;

    @Schema(description  = "分钟")
    private Integer timeMinute;

    @Schema(description  = "秒")
    private Integer timeSecond;

    @Schema(description  = "cron表达式")
    private String cronText;

    @Schema(description  = "是否启动: 1启动 2停止")
    private Integer enabled;

    @Schema(description  = "中文执行时间, 展示用")
    @TableField(exist = false)
    private String cronChinese;

    /**
     * 获取Job的入参名称或组名称
     * @param type
     * @param id
     * @return
     */
    public static String generateJobName(Integer type, String id) {
        String result = "";
        if (SchemaUsedEnum.TASK_JOB_NAME.getIndex().equals(type)) {
            result = SchemaUsedEnum.TASK_JOB_NAME.getDescribe() + id;
        }
        if (SchemaUsedEnum.TASK_JOB_GROUP_NAME.getIndex().equals(type)) {
            result = SchemaUsedEnum.TASK_JOB_GROUP_NAME.getDescribe() + id;
        }
        if (SchemaUsedEnum.TASK_JOB_TRIGGER_NAME.getIndex().equals(type)) {
            result = SchemaUsedEnum.TASK_JOB_TRIGGER_NAME.getDescribe() + id;
        }
        if (SchemaUsedEnum.TASK_JOB_TRIGGER_GROUP_NAME.getIndex().equals(type)) {
            result = SchemaUsedEnum.TASK_JOB_TRIGGER_GROUP_NAME.getDescribe() + id;
        }
        return result;
    }

}
