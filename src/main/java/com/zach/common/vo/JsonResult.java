package com.zach.common.vo;

import com.zach.common.constant.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Optional;

import static com.zach.common.constant.ResultCode.COMMON_FAIL;
import static com.zach.common.constant.ResultCode.SUCCESS;

/**
 * @author apple
 * @Description: 统一返回实体
 * @Date Create in 2019/7/22 19:20
 */
@Schema(description = "统一数据返回对象")
public class JsonResult<T> implements Serializable {
    @Schema(description = "请求成功/失败")
    private Boolean success;
    @Schema(description = "状态码")
    private Integer errorCode;
    @Schema(description = "描述")
    private String errorMsg;
    @Schema(description = "数据")
    private T data;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
        this.errorCode = success ? SUCCESS.getCode() : COMMON_FAIL.getCode();
        this.errorMsg = success ? SUCCESS.getMessage() : COMMON_FAIL.getMessage();
    }

    public JsonResult(boolean success, ResultCode resultEnum) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : Optional.ofNullable(resultEnum).map(ResultCode::getCode).orElseGet(COMMON_FAIL::getCode);
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : Optional.ofNullable(resultEnum).map(ResultCode::getMessage).orElseGet(COMMON_FAIL::getMessage);
    }

    public JsonResult(boolean success, T data) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public JsonResult(boolean success, ResultCode resultEnum, T data) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : Optional.ofNullable(resultEnum).map(ResultCode::getCode).orElseGet(COMMON_FAIL::getCode);
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : Optional.ofNullable(resultEnum).map(ResultCode::getMessage).orElseGet(COMMON_FAIL::getMessage);
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}