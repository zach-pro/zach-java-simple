package com.sensor.modular.quartz.task_journal.vo.response;


import com.sensor.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyh
 * @since 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiTaskJournalResponse extends BaseEntity<AiTaskJournalResponse> {

    @Schema(description  = "任务类型 1:自动 2:手动")
    private Integer taskMode;

    @Schema(description  = "执行状态: 1:成功 2:失败 3:进行中")
    private Integer taskStatus;

    @Schema(description  = "批次")
    private Integer taskBatch;

    @Schema(description  = "开始时间")
    private String startTime;

    @Schema(description  = "结束时间")
    private String endTime;

    @Schema(description  = "项目Id")
    private String projectId;

    @Schema(description  = "项目名称")
    private String projectName;
}
