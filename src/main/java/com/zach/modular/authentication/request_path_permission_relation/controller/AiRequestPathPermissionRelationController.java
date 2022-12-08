package com.zach.modular.authentication.request_path_permission_relation.controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 路径权限关联表 前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@RestController
@RequestMapping("/ai_request_path_permission_relation/aiRequestPathPermissionRelation")
@Transactional(rollbackFor=Exception.class)
public class AiRequestPathPermissionRelationController {

}
