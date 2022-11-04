package com.sensor.modular.quartz.task_journal.vo.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AiChangeTaskJournalRequest {
    @Schema(description  = "主键Id")
    private String id;

    @Schema(description  = "执行状态: 1:成功 2:失败")
    private Integer taskStatus;

}
