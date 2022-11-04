package com.sensor.modular.authentication.role_menu_relation.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * @since 2022-08-04
 */
@Tag(name = "ai_role_menu_relation", description = "角色与菜单关联模块")
@RestController
@RequestMapping("/ai_role_menu_relation/aiRoleMenuRelation")
@Transactional(rollbackFor=Exception.class)
public class AiRoleMenuRelationController {

}
