package com.sensor.modular.authentication.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.authentication.menu.service.impl.AiMenuServiceImpl;
import com.sensor.modular.authentication.permission.service.impl.AiPermissionServiceImpl;
import com.sensor.modular.authentication.role.entity.AiRole;
import com.sensor.modular.authentication.role.service.impl.AiRoleServiceImpl;
import com.sensor.modular.authentication.role_menu_relation.entity.AiRoleMenuRelation;
import com.sensor.modular.authentication.role_menu_relation.service.impl.AiRoleMenuRelationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * @since 2022-07-28
 */
@Tag(name = "ai_role", description = "角色模块")
@RestController
@RequestMapping("/ai_role/aiRole")
@Transactional(rollbackFor=Exception.class)
public class AiRoleController {

    private final AiRoleServiceImpl aiRoleService;
    private final AiMenuServiceImpl aiMenuService;
    private final AiPermissionServiceImpl aiPermissionService;
    private final AiRoleMenuRelationServiceImpl aiRoleMenuRelationService;

    public AiRoleController(AiRoleServiceImpl aiRoleService, AiMenuServiceImpl aiMenuService, AiPermissionServiceImpl aiPermissionService, AiRoleMenuRelationServiceImpl aiRoleMenuRelationService) {
        this.aiRoleService = aiRoleService;
        this.aiMenuService = aiMenuService;
        this.aiPermissionService = aiPermissionService;
        this.aiRoleMenuRelationService = aiRoleMenuRelationService;
    }

    @PostMapping("/saveRole")
    @Operation(summary  = "新建角色")
    public JsonResult<AiRole> saveRole(@RequestBody AiRole role){
        role.preInsert();
        List<AiRoleMenuRelation> relations  = new ArrayList<>();
        if (aiRoleService.save(role)) {
            String roleId = role.getId();
            AiRoleMenuRelation roleMenuRelation;
            // 关联的菜单
            List<String> menuIdList = role.getMenuIdList();
            for (String menuId : menuIdList) {
                roleMenuRelation = new AiRoleMenuRelation();
                roleMenuRelation.setRoleId(roleId);
                roleMenuRelation.setMenuId(menuId);
                roleMenuRelation.preInsert();
                relations.add(roleMenuRelation);
            }
            aiRoleMenuRelationService.saveBatch(relations);
            // 关联的权限
            aiPermissionService.saveRoleAllPermission(roleId);
        }
        return ResultTool.success(role);
    }

    @PostMapping("/delRole")
    @Operation(summary  = "删除角色")
    @Parameters(
            @Parameter(name = "id", description = "角色Id", required = true)
    )
    public JsonResult delRole(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<AiRole> qw = new LambdaQueryWrapper<>();
        qw.eq(AiRole::getId,id);
        if (aiRoleService.remove(qw)) {
            Map<String,Object> map = new HashMap<>(2);
            map.put("role_id",id);
            aiRoleMenuRelationService.removeByMap(map);
            aiPermissionService.removeRoleAllPermission(id);
        }
        return ResultTool.success();
    }
    @PostMapping("/upRole")
    @Operation(summary  = "修改角色")
    public JsonResult<AiRole> upProject(@RequestBody AiRole aiRole){
        if (aiRole == null || StringUtils.isBlank(aiRole.getId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        String roleId = aiRole.getId();
        if (aiRoleService.updateById(aiRole)) {
            Map<String,Object> map = new HashMap<>(2);
            map.put("role_id",roleId);
            if (aiRoleMenuRelationService.removeByMap(map)) {
                List<AiRoleMenuRelation> relations  = new ArrayList<>();
                AiRoleMenuRelation roleMenuRelation;
                // 关联的菜单
                List<String> menuIdList = aiRole.getMenuIdList();
                for (String menuId : menuIdList) {
                    roleMenuRelation = new AiRoleMenuRelation();
                    roleMenuRelation.setRoleId(roleId);
                    roleMenuRelation.setMenuId(menuId);
                    roleMenuRelation.preInsert();
                    relations.add(roleMenuRelation);
                }
                aiRoleMenuRelationService.saveBatch(relations);
            }
        }
        return ResultTool.success(aiRole);
    }

    @PostMapping("/selRoleLists")
    @Operation(summary  = "查询角色列表")
    public JsonResult<List<AiRole>> selRoleLists(@RequestBody AiRole aiRole){
        LambdaQueryWrapper<AiRole> qw = new LambdaQueryWrapper<>();
        qw.setEntity(aiRole);
        return ResultTool.success(aiRoleService.list(qw));
    }

    @PostMapping("/selRoleInfo")
    @Operation(summary  = "查询角色详情")
    @Parameters(
            @Parameter(name = "id", description = "角色Id", required = true)
    )
    public JsonResult<AiRole> selRoleInfo(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        AiRole role = aiRoleService.getById(id);
        if (role != null) {
            role.setMenuList(aiMenuService.selMenuByRoleId(id));
        }
        return ResultTool.success(role);
    }
}
