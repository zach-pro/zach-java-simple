package com.zach.common.config.security.handler;

/**
 * @author by apple
 * @Classname CustomizeAuthenticationSuccessHandler
 * @Description TODO
 * @Date 2022/7/29 15:27
 */

import com.alibaba.fastjson.JSON;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.authentication.menu.entity.AiMenu;
import com.zach.modular.authentication.menu.service.impl.AiMenuServiceImpl;
import com.zach.modular.authentication.user.entity.AiUser;
import com.zach.modular.authentication.user.service.impl.AiUserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: apple
 * @Description: 登录成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    final AiUserServiceImpl aiUserService;
    final AiMenuServiceImpl aiMenuService;

    public CustomizeAuthenticationSuccessHandler(AiUserServiceImpl aiUserService, AiMenuServiceImpl aiMenuService) {
        this.aiUserService = aiUserService;
        this.aiMenuService = aiMenuService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AiUser aiUser = aiUserService.loadUserByName(userDetails.getUsername());
        aiUser.setLastLoginTime(LocalDateTime.now() + "");
        aiUserService.updateById(aiUser);
        List<AiMenu> aiMenus = aiMenuService.selMenuByRoleId(aiUser.getRoleId());
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
        HttpSession session = httpServletRequest.getSession();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("sessionId",session.getId());
        paramMap.put("user",aiUser);
        paramMap.put("menus",aiMenus);
        //返回json数据
        JsonResult result = ResultTool.success(paramMap);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}