package com.sensor.modular.nebula.entity;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Nebula-Graph session json 查询返回结果反序列化。
 * 该序列化非通用模式，需要具体分析 json 结果。
 * @author apple
 */
@Data
@Schema(description ="返回类")
public class NebulaVertexJsonResult {
    @Schema(description ="空间名称")
    private String spaceName;
    @Schema(description ="潜伏期")
    private int latencyInUs;
    @Schema(description ="数据集合")
    private List<OneData> data;
    @Schema(description ="字段")
    private List<String> columns;

    public Set<FailureTroubleshootingVo> toFailureTroubleshootingVos() {
        final List<OneData> dataList = this.getData();
        final Set<FailureTroubleshootingVo> r = Sets.newHashSetWithExpectedSize(dataList.size());
        for (OneData oneData : dataList) {
            final List<OneMeta> meta = oneData.getMeta();
            final List<LinkedHashMap<String, String>> row = oneData.getRow();
            for (int i = 0; i < meta.size(); i++) {
                final Map<String, String> oneRow = row.get(i);
                final Map<String, String> properties = Maps.newHashMapWithExpectedSize(oneRow.size());
                String tag = "unknown";
                for (Map.Entry<String, String> entry : oneRow.entrySet()) {
                    final String key = entry.getKey();
                    final String[] split = key.split("\\.");
                    tag = split[0];
                    properties.put(split[1], entry.getValue());
                }
                r.add(FailureTroubleshootingVo.builder()
                    .id(meta.get(i).getId())
                    .tag(tag)
                    .properties(properties)
                    .build());
            }
        }
        return r;
    }

    @Data
    public static class OneData {
        @Schema(description ="元数据")
        private List<OneMeta> meta;
        @Schema(description ="属性名称: 属性值")
        private List<LinkedHashMap<String, String>> row;
    }

    @Data
    public static class OneMeta {
        @Schema(description ="id")
        private String id;
        @Schema(description ="类型")
        private String type;
    }
}

