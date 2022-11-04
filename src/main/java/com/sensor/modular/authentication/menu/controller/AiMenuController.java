package com.sensor.modular.authentication.menu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.constant.ResultCustomCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.authentication.menu.entity.AiMenu;
import com.sensor.modular.authentication.menu.service.impl.AiMenuServiceImpl;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author apple
 */
@Tag(name = "ai_menu", description = "菜单模块")
@RestController
@RequestMapping("/ai_menu/aiMenu")
@Transactional(rollbackFor=Exception.class)
public class AiMenuController {

    private final AiMenuServiceImpl menuService;
    private final AiRoleMenuRelationServiceImpl roleMenuRelationService;

    public AiMenuController(AiMenuServiceImpl menuService, AiRoleMenuRelationServiceImpl roleMenuRelationService) {
        this.menuService = menuService;
        this.roleMenuRelationService = roleMenuRelationService;
    }

    @PostMapping("/saveMenu")
    @Operation(summary  = "新建菜单")
    public JsonResult<AiMenu> saveMenu(@RequestBody AiMenu aiMenu){
        aiMenu.preInsert();
        menuService.save(aiMenu);
        return ResultTool.success(aiMenu);
    }

    @PostMapping("/delMenu")
    @Operation(summary  = "删除菜单")
    @Parameters(
            @Parameter(name = "id", description = "菜单Id", required = true)
    )
    public JsonResult delMenu(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<AiMenu> lwq = new LambdaQueryWrapper<>();
        lwq.eq(AiMenu::getParentId,id);
        List<AiMenu> list = menuService.list(lwq);
        if (!list.isEmpty()) {
            return ResultTool.fail(ResultCustomCode.PARAM_MENU_NOT_VALID);
        }
        LambdaQueryWrapper<AiMenu> qw = new LambdaQueryWrapper<>();
        qw.eq(AiMenu::getId,id);
        menuService.remove(qw);
        Map<String,Object> map = new HashMap<>(2);
        map.put("menu_id",id);
        roleMenuRelationService.removeByMap(map);
        return ResultTool.success();
    }

    @PostMapping("/upMenu")
    @Operation(summary  = "修改菜单")
    public JsonResult<AiMenu> upMenu(@RequestBody AiMenu aiMenu){
        if (aiMenu ==null || StringUtils.isBlank(aiMenu.getId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        menuService.updateById(aiMenu);
        return ResultTool.success(aiMenu);
    }


    @PostMapping("/selMenuLists")
    @Operation(summary  = "查询菜单列表")
    public JsonResult<List<AiMenu>> selMenuLists(){
        return ResultTool.success(menuService.selectSubclassMenu());
    }

    @PostMapping("/selParentMenuLists")
    @Operation(summary  = "查询父级菜单列表")
    public JsonResult<List<AiMenu>> selParentMenuLists(){
        return ResultTool.success(menuService.selParentMenuLists());
    }

    @PostMapping("/selMenuInfo")
    @Operation(summary  = "查询菜单详情")
    @Parameters(
            @Parameter(name = "id", description = "菜单Id", required = true)
    )
    public JsonResult<AiMenu> selMenuInfo(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(menuService.selectMenuOne(id));
    }
}
