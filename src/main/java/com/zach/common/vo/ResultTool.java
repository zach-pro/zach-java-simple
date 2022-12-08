package com.zach.common.vo;

import com.zach.common.constant.ResultCode;
import com.zach.common.constant.ResultCustomCode;

/**
 * @author by apple
 * @Classname ResultTool
 * @Description 返回包装类
 */
public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }

    public static JsonResult fail(ResultCustomCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
