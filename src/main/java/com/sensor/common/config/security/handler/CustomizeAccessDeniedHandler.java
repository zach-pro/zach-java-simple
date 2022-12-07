package com.sensor.common.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by apple
 * @Classname CustomizeAccessDeniedHandler
 * @Description 自定义权限拒绝策略
 * @Date 2022/7/29 17:11
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
