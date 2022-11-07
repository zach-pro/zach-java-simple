package com.sensor.modular.authentication.request_path.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.authentication.permission.entity.AiPermission;
import com.sensor.modular.authentication.permission.service.impl.AiPermissionServiceImpl;
import com.sensor.modular.authentication.request_path.entity.AiRequestPath;
import com.sensor.modular.authentication.request_path.service.impl.AiRequestPathServiceImpl;
import com.sensor.modular.authentication.request_path_permission_relation.entity.AiRequestPathPermissionRelation;
import com.sensor.modular.authentication.request_path_permission_relation.service.impl.AiRequestPathPermissionRelationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 请求路径 前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@Tag(name = "ai_request_path", description = "请求路径管理模块")
@RestController
@RequestMapping("/ai_request_path/aiRequestPath")
@Transactional(rollbackFor=Exception.class)
public class AiRequestPathController {
    private final RestTemplate restTemplate;
    private final AiPermissionServiceImpl aiPermissionService;
    private final AiRequestPathServiceImpl aiRequestPathService;
    private final AiRequestPathPermissionRelationServiceImpl aiRequestPathPermissionRelationService;

    public AiRequestPathController(AiPermissionServiceImpl aiPermissionService, AiRequestPathServiceImpl aiRequestPathService, RestTemplate restTemplate, AiRequestPathPermissionRelationServiceImpl aiRequestPathPermissionRelationService) {
        this.aiPermissionService = aiPermissionService;
        this.aiRequestPathService = aiRequestPathService;
        this.restTemplate = restTemplate;
        this.aiRequestPathPermissionRelationService = aiRequestPathPermissionRelationService;
    }

    @PostMapping("/saveRequestPath")
    @Operation(summary  = "新建请求路径")
    public JsonResult<AiRequestPath> saveRequestPath(@RequestBody AiRequestPath aiRequestPath){
        if (StringUtils.isBlank(aiRequestPath.getUrl())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        aiRequestPath.preInsert();
        String tagName = aiRequestPath.getTagName();
        if(StringUtils.isNotBlank(tagName)){
            aiRequestPath.setTagRank(aiRequestPathService.selTagNextRank(tagName));
        }
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

    @GetMapping("/selSwaggerApiDocAdd")
    @Operation(summary  = "Api赋权")
    public JsonResult selSwaggerApiDocAdd(){
        // 请求服务地址
        String modelHref = "http://127.0.0.1:9300/api-docs";
        String result = restTemplate.getForObject(modelHref, String.class);
        JSONObject paths = (JSONObject) Objects.requireNonNull(JSON.parseObject(result)).get("paths");
        List<AiPermission> allPermissions = aiPermissionService.list(new LambdaQueryWrapper<>());
        Map<String,Integer> tagMap = new HashMap<>(paths.entrySet().size());
        for (Map.Entry<String, Object> entry : paths.entrySet()) {
            AiRequestPath requestPath = new AiRequestPath();
            JSONObject value = (JSONObject) entry.getValue();
            for (Map.Entry<String, Object> obj : value.entrySet()) {
                requestPath.setUrl(entry.getKey());
                JSONObject postObject = (JSONObject) obj.getValue();
                Object summary = postObject.get("summary");
                Object desc = summary != null ? summary : "";
                requestPath.setDescription(String.valueOf(desc));
                JSONArray tags = (JSONArray) postObject.get("tags");
                for (Object tag : tags) {
                    Integer compute = tagMap.compute(tag.toString(), (k, v) -> (v == null) ? 0 : ++v);
                    requestPath.setTagName(tag.toString());
                    requestPath.setTagRank(compute);
                }
            }
            requestPath.preInsert();
            aiRequestPathService.save(requestPath);
            AiRequestPathPermissionRelation requestPathPermissionRelation;
            for (AiPermission permission : allPermissions) {
                requestPathPermissionRelation = new AiRequestPathPermissionRelation();
                requestPathPermissionRelation.setUrlId(requestPath.getId());
                requestPathPermissionRelation.setPermissionId(permission.getId());
                requestPathPermissionRelation.preInsert();
                aiRequestPathPermissionRelationService.save(requestPathPermissionRelation);
            }
        }
        return ResultTool.success(result);
    }

}
