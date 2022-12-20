package com.zach.modular.elastic.controller;

import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.elastic.entity.RealTimeInfo;
import com.zach.modular.elastic.entity.UpdateRealTimeInfo;
import com.zach.modular.elastic.service.impl.ElasticServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author apple
 */
@Tag(name = "Elastic服务")
@RestController
@RequestMapping("/es")
@Transactional(rollbackFor=Exception.class)
public class ElasticController {
    @Autowired
    private ElasticServiceImpl elasticService;

    @PostMapping("/createIndex")
    @Operation(summary = "创建索引")
    public JsonResult createIndex(String index) throws IOException {
        return ResultTool.success(elasticService.createIndex(index));
    }

    @PostMapping("/createDocument")
    @Operation(summary = "添加文档")
    public JsonResult createDocument(@RequestBody RealTimeInfo info) throws IOException {
        return elasticService.createDocument(info);
    }

    @PostMapping("/updateDocument")
    @Operation(summary = "修改文档")
    public JsonResult updateDocument(@RequestBody UpdateRealTimeInfo info) throws IOException {
        return elasticService.updateDocument(info.getDocId(),info.getRealTimeInfo());
    }

    @PostMapping("/existDocumentTest")
    @Operation(summary = "文档是否存在")
    public JsonResult existDocumentTest(String id) throws IOException {
        return elasticService.existDocumentTest(id);
    }

    @PostMapping("/getDocument")
    @Operation(summary = "获取文档")
    public JsonResult getDocument(String id) throws IOException {
        return elasticService.getDocument(id);
    }

    @PostMapping("/deleteDocument")
    @Operation(summary = "删除文档")
    public JsonResult deleteDocument(String id) throws IOException {
        return elasticService.deleteDocument(id);
    }

    @PostMapping("/bulkDocument")
    @Operation(summary = "批量添加")
    public JsonResult bulkDocument(@RequestBody List<RealTimeInfo> list) throws IOException {
        return elasticService.bulkDocument(list);
    }

    @PostMapping("/searchDocument")
    @Operation(summary = "搜索文档")
    public JsonResult searchDocument(String text) throws IOException {
        return ResultTool.success(elasticService.searchDocument(text));
    }

    @PostMapping("/searchHighlight")
    @Operation(summary = "高亮查询")
    public JsonResult searchHighlight(String text) throws IOException {
        return elasticService.searchHighlight(text);
    }

}