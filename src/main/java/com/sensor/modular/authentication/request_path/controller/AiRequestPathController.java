package com.sensor.modular.authentication.request_path.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.authentication.permission.service.impl.AiPermissionServiceImpl;
import com.sensor.modular.authentication.request_path.entity.AiRequestPath;
import com.sensor.modular.authentication.request_path.service.impl.AiRequestPathServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 请求路径 前端控制器
 * </p>
 *
 * @author zyh
 * @since 2022-07-29
 */
@Tag(name = "ai_request_path", description = "请求路径管理模块")
@RestController
@RequestMapping("/ai_request_path/aiRequestPath")
@Transactional(rollbackFor=Exception.class)
public class AiRequestPathController {
    @Autowired
    private AiPermissionServiceImpl aiPermissionService;
    @Autowired
    private AiRequestPathServiceImpl aiRequestPathService;

    @PostMapping("/saveRequestPath")
    @Operation(summary  = "新建请求路径")
    public JsonResult<AiRequestPath> saveRequestPath(@RequestBody AiRequestPath aiRequestPath){
        if (StringUtils.isBlank(aiRequestPath.getUrl())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        aiRequestPath.preInsert();
        if (aiRequestPathService.save(aiRequestPath)) {
            aiPermissionService.saveRequestPathAllPermission(aiRequestPath.getId());
        }
        return ResultTool.success(aiRequestPath);
    }

    @PostMapping("/delRequestPath")
    @Operation(summary  = "删除请求路径")
    @Parameters(
            @Parameter(name = "id", description = "请求路径Id", required = true)
    )
    public JsonResult delRequestPath(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<AiRequestPath> qw = new LambdaQueryWrapper<>();
        qw.eq(AiRequestPath::getId,id);
        aiRequestPathService.remove(qw);
        aiPermissionService.removeRequestPathAllPermission(id);
        return ResultTool.success();
    }

    @PostMapping("/upRequestPath")
    @Operation(summary  = "修改请求路径")
    public JsonResult<AiRequestPath> upRequestPath(@RequestBody AiRequestPath aiRequestPath){
        if (aiRequestPath ==null || StringUtils.isBlank(aiRequestPath.getId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        aiRequestPathService.updateById(aiRequestPath);
        return ResultTool.success(aiRequestPath);
    }

    @PostMapping("/selRequestPathList")
    @Operation(summary  = "查询请求路径列表")
    public JsonResult<List<AiRequestPath>> selRequestPathList(@RequestBody AiRequestPath aiRequestPath){
        LambdaQueryWrapper<AiRequestPath> qw = new LambdaQueryWrapper<>();
        qw.setEntity(aiRequestPath);
        return ResultTool.success(aiRequestPathService.list(qw));
    }

    @PostMapping("/selRequestPath")
    @Operation(summary  = "查询请求路径详情")
    @Parameters(
            @Parameter(name = "id", description = "请求路径Id", required = true)
    )
    public JsonResult<AiRequestPath> selRequestPath(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(aiRequestPathService.getById(id));
    }
}
