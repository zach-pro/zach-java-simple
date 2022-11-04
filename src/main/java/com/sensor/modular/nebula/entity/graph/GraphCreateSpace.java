package com.sensor.modular.nebula.entity.graph;

import cn.hutool.core.text.CharSequenceUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author apple
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="创建空间实体")
public class GraphCreateSpace {

    /**
     * 空间名称
     **/
    @Schema(description ="空间名称", example = "sense",required = true)
    private String space;

    /**
     * 空间中文名称
     **/
    @Schema(description ="空间中文名称", example = "测试",required = true)
    private String spaceChineseName;
    /**
     * 分片数量
     **/
    @Schema(description ="分片数量", example = "1")
    private Integer partitionNum;

    /**
     * 分片数量
     **/
    @Schema(description ="副本数量", example = "1")
    private Integer replicaFactor;
    /**
     * 类型
     **/
    @Builder.Default
    @Schema(description ="点类型:INT64,FIXED_STRING", example = "INT64")
    private String fixedType = "INT64";
    /**
     * 类型大小
     **/
    @Schema(description ="类型长度,FIXED_STRING 此字段必填 如32,64")
    private String size;
    /**
     * 描述
     **/
    @Schema(description ="描述")
    private String comment;

    public GraphCreateSpace(String space, String comment) {
        this.space = space;
        this.spaceChineseName = space;
        this.partitionNum = 100;
        this.replicaFactor = 3;
        this.fixedType = "FIXED_STRING";
        this.size = "64";
        this.comment = comment;
    }

    public String getSize() {
        if (CharSequenceUtil.isNotBlank(size)) {
            return "(" + size + ")";
        }
        return "";
    }
}
