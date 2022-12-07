package com.emas.common.vo;

import com.emas.common.constant.ResultCode;
import com.emas.common.constant.ResultCustomCode;

/**
 * @author by apple
 * @Classname ResultTool
 * @Description 返回包装类
 */
public class ResultToolPage {
    public static JsonResultPage success() {
        return new JsonResultPage(true);
    }

    public static <T> JsonResultPage<T> success(T data) {
        return new JsonResultPage(true, data);
    }

    public static <T> JsonResultPage<T> success(T data,Integer pageNum,Integer pageSize,Integer total,Integer totalPage) {
        return new JsonResultPage(true, data, pageNum, pageSize, total, totalPage);
    }

    public static JsonResultPage fail() {
        return new JsonResultPage(false);
    }

    public static JsonResultPage fail(ResultCode resultEnum) {
        return new JsonResultPage(false, resultEnum);
    }

    public static JsonResultPage fail(ResultCustomCode resultEnum) {
        return new JsonResultPage(false, resultEnum);
    }
}
