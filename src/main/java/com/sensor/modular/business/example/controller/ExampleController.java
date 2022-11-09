package com.sensor.modular.business.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sensor.common.base.BasePage;
import com.sensor.common.constant.ResultCode;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.business.example.entity.Example;
import com.sensor.modular.business.example.service.impl.ExampleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * @since 2022-11-09
 */
@Tag(name = "示例模块")
@RestController
@RequestMapping("/example")
@Transactional(rollbackFor = Exception.class)
public class ExampleController {
    private final ExampleServiceImpl exampleService;
    public ExampleController(ExampleServiceImpl exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping("/saveExample")
    @Operation(summary = "新建示例")
    public JsonResult saveExample(@RequestBody Example example){
        example.preInsert();
        exampleService.save(example);
        return ResultTool.success(example);
    }

    @PostMapping("/delExample")
    @Operation(summary = "删除示例")
    @Parameter(name = "id", description = "示例编号", required = true)
    public JsonResult delExample(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<Example> qw = new LambdaQueryWrapper<>();
        qw.eq(Example::getId,id);
        exampleService.remove(qw);
        return ResultTool.success();
    }

    @PostMapping("/upExample")
    @Operation(summary = "修改示例")
    public JsonResult upExample(@RequestBody Example example){
        if (example == null || StringUtils.isBlank(example.getId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        exampleService.updateById(example);
        return ResultTool.success(example);
    }

    @PostMapping("/selExampleLists")
    @Operation(summary = "查询示例列表")
    public JsonResult selExampleLists(){
        return ResultTool.success(exampleService.list());
    }

    @PostMapping("/selExamplePage")
    @Operation(summary = "分页查询")
    public JsonResult selExamplePage(@RequestBody BasePage pageInfo){
        Page<Example> page = new Page<>(pageInfo.getCurrentPage(), pageInfo.getPageSize());
        return ResultTool.success(exampleService.page(page));
    }

    @PostMapping("/selExampleInfo")
    @Operation(summary = "查询示例详情")
    @Parameter(name = "id", description = "示例编号", required = true)
    public JsonResult selExampleInfo(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(exampleService.getById(id));
    }

}
