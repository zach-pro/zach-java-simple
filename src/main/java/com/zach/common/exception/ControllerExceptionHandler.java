package com.zach.common.exception;


import com.zach.common.constant.ResultCode;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author apple
 * 项目全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult paramException(BindException e) {
        FieldError fieldError = e.getFieldError();
        String field = fieldError != null ? fieldError.getField() : "";
        String messageId = fieldError != null ? fieldError.getDefaultMessage() : "";
        log.error(e.getMessage() + field + messageId);
        return ResultTool.fail(ResultCode.PARAM_NOT_VALID);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public JsonResult IllegalArgumentException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResultTool.fail(ResultCode.PARAM_NOT_CHECK);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public JsonResult nullException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult customException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResultTool.fail(ResultCode.COMMON_FAIL);
    }
}
