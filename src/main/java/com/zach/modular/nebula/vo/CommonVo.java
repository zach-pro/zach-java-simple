package com.zach.modular.nebula.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Descriptin: 通用返回类
 * @ClassName: CommonVo
 */
@Schema(description ="操作通用返回类")
public class CommonVo {

    @Schema(description = "空间名称")
    private String spaceName;
    @Schema(description = "延迟单位")
    private int latencyInUs;
    private ErrorsBean errors;

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
}
