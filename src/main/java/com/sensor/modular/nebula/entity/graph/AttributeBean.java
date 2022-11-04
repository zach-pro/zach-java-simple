package com.sensor.modular.nebula.entity.graph;

import cn.hutool.core.text.CharSequenceUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author apple
 */
@Schema(description ="属性入参")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AttributeBean {

    /**
     * 属性名称
     **/
    @Schema(description ="tag/edge的属性名称", example = "p3", required = true)
    private String propertyName;
    /**
     * 属性类型 (int bool string double .........)
     **/
    @Schema(description ="索引长度:属性为string 必须有长度,其他类型不可传入 ", example = "30", required = true)
    private String indexLength;

    public String getPropertyName() {
        return "`"+propertyName +"`"+ getIndexLength();
    }

    private String getIndexLength() {
        if (CharSequenceUtil.isNotBlank(indexLength)) {
            return "(" + indexLength + ")";
        }
        return "";
    }

    private String getIndexFull() {
        if (CharSequenceUtil.isNotBlank(indexLength)) {
            return "(" + indexLength + ")";
        }
        return "";
    }
}
