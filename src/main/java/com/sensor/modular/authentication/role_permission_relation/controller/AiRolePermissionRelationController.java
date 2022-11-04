package com.sensor.modular.authentication.role_permission_relation.controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色-权限关联关系表 前端控制器
 * </p>
 *
 * @author zyh
 * @since 2022-07-29
 */
@RestController
@RequestMapping("/ai_role_permission_relation/aiRolePermissionRelation")
@Transactional(rollbackFor=Exception.class)
public class AiRolePermissionRelationController {

}
