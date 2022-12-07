package com.emas.modular.emas.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emas.common.base.BasePage;
import com.emas.common.constant.ResultCode;
import com.emas.common.vo.JsonResult;
import com.emas.common.vo.ResultTool;
import com.emas.modular.emas.entity.Emas;
import com.emas.modular.emas.service.impl.EmasServiceImpl;
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
 * @since 2022-12-06
 */
@Tag(name = "示例模块")
@RestController
@RequestMapping("/emas")
@Transactional(rollbackFor = Exception.class)
public class EmasController {
    private final EmasServiceImpl emasService;
    public EmasController(EmasServiceImpl emasService) {
        this.emasService = emasService;
    }

    @PostMapping("/saveEmas")
    @Operation(summary = "新建示例")
    public JsonResult saveEmas(@RequestBody Emas emas){
        emas.preInsert();
        emasService.save(emas);
        return ResultTool.success(emas);
    }

    @PostMapping("/delEmas")
    @Operation(summary = "删除示例")
    @Parameter(name = "id", description = "示例编号", required = true)
    public JsonResult delEmas(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<Emas> qw = new LambdaQueryWrapper<>();
        qw.eq(Emas::getId,id);
        emasService.remove(qw);
        return ResultTool.success();
    }

    @PostMapping("/upEmas")
    @Operation(summary = "修改示例")
    public JsonResult upEmas(@RequestBody Emas emas){
        if (emas == null || StringUtils.isBlank(emas.getId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        emasService.updateById(emas);
        return ResultTool.success(emas);
    }

    @PostMapping("/selEmasLists")
    @Operation(summary = "查询示例列表")
    public JsonResult selEmasLists(){
        return ResultTool.success(emasService.list());
    }

    @PostMapping("/selEmasPage")
    @Operation(summary = "分页查询")
    public JsonResult selEmasPage(@RequestBody BasePage pageInfo){
        Page<Emas> page = new Page<>(pageInfo.getCurrentPage(), pageInfo.getPageSize());
        return ResultTool.success(emasService.page(page));
    }

    @PostMapping("/selEmasInfo")
    @Operation(summary = "查询示例详情")
    @Parameter(name = "id", description = "示例编号", required = true)
    public JsonResult selEmasInfo(String id){
        if(StringUtils.isBlank(id)){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(emasService.getById(id));
    }

}
