package com.zach.modular.nebula.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 故障图谱归因分析-失效现象解析结果
 * @author apple
 */
@Builder
@Data
@EqualsAndHashCode(of = "id")
public class FailureTroubleshootingVo {
    private String id;
    private String tag;
    private Map<String, String> properties;
}
