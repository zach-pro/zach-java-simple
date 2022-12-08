package com.zach.modular.authentication.permission.controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@RestController
@RequestMapping("/ai_permission/aiPermission")
@Transactional(rollbackFor=Exception.class)
public class AiPermissionController {

}
