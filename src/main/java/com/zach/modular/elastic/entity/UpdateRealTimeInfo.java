package com.zach.modular.elastic.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Classname RealTimeInfo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRealTimeInfo {
    @Schema(description = "docId")
    private String docId;

    @Schema(description = "数据")
    private RealTimeInfo realTimeInfo;
}
