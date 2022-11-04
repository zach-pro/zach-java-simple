package com.sensor.modular.nebula.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * @author apple
 */
@Schema(description ="属性查询返回类")
public class AttributeVo {

    @Schema(description = "空间名称")
    private String spaceName;
    @Schema(description = "延迟单位" )
    private int latencyInUs;
    private ErrorsBean errors;
    @Schema(description = "查询返回属性集合")
    private List<DataBean> data;
    @Schema(description = "返回字段名集合")
    private List<String> columns;

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public int getLatencyInUs() {
        return latencyInUs;
    }

    public void setLatencyInUs(int latencyInUs) {
        this.latencyInUs = latencyInUs;
    }

    public ErrorsBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorsBean errors) {
        this.errors = errors;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public static class ErrorsBean {

        @Schema(description = "错误码0正常")
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class DataBean {
        @Schema(description = "元数据")
        private List<String> meta;
        @Schema(description = "字段值集合")
        private List<String> row;

        public List<String> getMeta() {
            return meta;
        }

        public void setMeta(List<String> meta) {
            this.meta = meta;
        }

        public List<String> getRow() {
            return row;
        }

        public void setRow(List<String> row) {
            this.row = row;
        }
    }

    private Map<String, List<String>> fieldMap;

    public Map<String, List<String>> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, List<String>> fieldMap) {
        this.fieldMap = fieldMap;
    }
}
