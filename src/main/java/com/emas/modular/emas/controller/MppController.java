package com.emas.modular.emas.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emas.common.base.BasePage;
import com.emas.common.constant.ResultCode;
import com.emas.common.vo.JsonResult;
import com.emas.common.vo.JsonResultPage;
import com.emas.common.vo.ResultTool;
import com.emas.common.vo.ResultToolPage;
import com.emas.modular.emas.entity.Emas;
import com.emas.modular.emas.request.EmasInsertBatchRequest;
import com.emas.modular.emas.request.EmasListRequest;
import com.emas.modular.emas.request.EmasListRequestPage;
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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * @since 2022-12-06
 */
@Tag(name = "数据访问")
@RestController
@RequestMapping("/emas-dbserver/mpp")
@Transactional(rollbackFor = Exception.class)
public class MppController {
    private final EmasServiceImpl emasService;
    public MppController(EmasServiceImpl emasService) {
        this.emasService = emasService;
    }

    @PostMapping("/queryForList")
    @Operation(summary = "queryForList")
    public JsonResult queryForList(@RequestBody EmasListRequest request){
        return ResultTool.success(emasService.customListSql(request.getSql()));
    }

    @PostMapping("/queryForObject")
    @Operation(summary = "queryForObject")
    public JsonResult queryForObject(@RequestBody EmasListRequest request){
        List<Emas> emas = emasService.customListSql(request.getSql());
        return ResultTool.success(emas.get(0));
    }

    @PostMapping("/queryForPagination")
    @Operation(summary = "queryForPagination")
    public JsonResultPage queryForPagination(@RequestBody EmasListRequestPage request){
        Integer pageSize = request.getPageSize();
        Integer pageNum = request.getPageNum();
        List<Emas> countEmas = emasService.customListSql(request.getSql());
        int total = countEmas.size();
        int totalPage = total / pageSize;

        String sql = request.getSql();
        int limit = sql.indexOf("limit");
        if (limit == -1) {
            sql = sql + "limit " + request.getPageSize()+ " offset " + request.getPageNum();
            request.setSql(sql);
        }
        List<Emas> emas = emasService.customListSql(request.getSql());
        return ResultToolPage.success(emas,pageNum,pageSize,total,totalPage);
    }

    @PostMapping("/insert")
    @Operation(summary = "insert")
    public JsonResult insert(@RequestBody EmasListRequest request){
        return ResultTool.success(emasService.customInsertSql(request.getSql()));
    }

    @PostMapping("/batchInsert")
    @Operation(summary = "batchInsert")
    public JsonResult batchInsert(@RequestBody EmasInsertBatchRequest request){
        StringBuilder sql = new StringBuilder();
        for (String s : request.getSql()) {
            sql.append(s);
        }
        return ResultTool.success(emasService.customInsertSql(sql.toString()));
    }

    @PostMapping("/update")
    @Operation(summary = "update")
    public JsonResult update(@RequestBody EmasListRequest request){
        return ResultTool.success(emasService.customUpdateSql(request.getSql()));
    }

    @PostMapping("/batchUpdate")
    @Operation(summary = "batchUpdate")
    public JsonResult batchUpdate(@RequestBody EmasInsertBatchRequest request){
        StringBuilder sql = new StringBuilder();
        for (String s : request.getSql()) {
            sql.append(s);
        }
        return ResultTool.success(emasService.customUpdateSql(sql.toString()));
    }

    @PostMapping("/delete")
    @Operation(summary = "delete")
    public JsonResult delete(@RequestBody EmasListRequest request){
        return ResultTool.success(emasService.customDeleteSql(request.getSql()));
    }

    @PostMapping("/batchDelete")
    @Operation(summary = "batchDelete")
    public JsonResult batchDelete(@RequestBody EmasInsertBatchRequest request){
        StringBuilder sql = new StringBuilder();
        for (String s : request.getSql()) {
            sql.append(s);
        }
        return ResultTool.success(emasService.customDeleteSql(sql.toString()));
    }







}
