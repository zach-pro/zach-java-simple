package com.sensor.modular.quartz.task_journal.vo.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class AiTaskJournalRequest {

    @Schema(description  = "任务类型 1:自动 2:手动")
    private Integer taskMode;

    @Schema(description  = "项目Id")
    private String projectId;

    @Schema(description  = "用户Id")
    private String createUserId;

    public AiTaskJournalRequest(Integer taskMode, String projectId) {
        this.taskMode = taskMode;
        this.projectId = projectId;
    }
}
