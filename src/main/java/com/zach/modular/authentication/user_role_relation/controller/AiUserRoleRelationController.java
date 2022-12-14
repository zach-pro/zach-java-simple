package com.zach.modular.authentication.user_role_relation.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色关联关系表 前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@Tag(name = "用户角色关联模块", description = "用户角色关联模块")
@RestController
@RequestMapping("/ai_user_role_relation/aiUserRoleRelation")
@Transactional(rollbackFor=Exception.class)
public class AiUserRoleRelationController {

}
