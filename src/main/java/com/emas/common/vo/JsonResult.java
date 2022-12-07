package com.emas.common.vo;

import com.emas.common.constant.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Optional;

import static com.emas.common.constant.ResultCode.COMMON_FAIL;
import static com.emas.common.constant.ResultCode.SUCCESS;

/**
 * @author apple
 * @Description: 统一返回实体
 * @Date Create in 2019/7/22 19:20
 */
@Schema(description = "统一数据返回对象")
public class JsonResult<T> implements Serializable {
    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "执行信息")
    private String msg;
    @Schema(description = "查询结果")
    private T result;

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.code = success ? SUCCESS.getCode() : COMMON_FAIL.getCode();
        this.msg = success ? SUCCESS.getMessage() : COMMON_FAIL.getMessage();
    }

    public JsonResult(boolean success, ResultCode resultEnum) {
        this.code = success ? ResultCode.SUCCESS.getCode() : Optional.ofNullable(resultEnum).map(ResultCode::getCode).orElseGet(COMMON_FAIL::getCode);
        this.msg = success ? ResultCode.SUCCESS.getMessage() : Optional.ofNullable(resultEnum).map(ResultCode::getMessage).orElseGet(COMMON_FAIL::getMessage);
    }

    public JsonResult(boolean success, T data) {
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.msg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
        this.result = data;
    }

    public JsonResult(boolean success, ResultCode resultEnum, T data) {
        this.code = success ? ResultCode.SUCCESS.getCode() : Optional.ofNullable(resultEnum).map(ResultCode::getCode).orElseGet(COMMON_FAIL::getCode);
        this.msg = success ? ResultCode.SUCCESS.getMessage() : Optional.ofNullable(resultEnum).map(ResultCode::getMessage).orElseGet(COMMON_FAIL::getMessage);
        this.result = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}