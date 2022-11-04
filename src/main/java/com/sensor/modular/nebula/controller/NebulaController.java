package com.sensor.modular.nebula.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import com.sensor.common.constant.AttributeEnum;
import com.sensor.common.constant.SchemaUsedEnum;
import com.sensor.common.utils.nebula.NebulaUtil;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import com.sensor.modular.nebula.entity.graph.*;
import com.sensor.modular.nebula.service.GraphCommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author by apple
 * @Classname TestController
 * @Description 图数据库操作
 * @Date 2022/8/17 17:30
 */
@Tag(name = "graph_nebula", description = "图数据库操作")
@RestController
@RequestMapping("/graph_nebula")
@Transactional(rollbackFor=Exception.class)
public class NebulaController {

    final GraphCommonService graphCommonService;

    public NebulaController(GraphCommonService graphCommonService) {
        this.graphCommonService = graphCommonService;
    }

    @PostMapping("/customStatement")
    @Operation(summary = "图数据库查询")
    public JsonResult customStatement(@RequestBody GraphCustomStatement customStatement) {
        String sqlCustom = customStatement.getCustomStatement();
        String trim = sqlCustom.trim();
        if (StringUtils.isBlank(trim)) {
            return ResultTool.fail();
        }
        customStatement.setCustomStatement(trim);
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.customStatement(customStatement), JSONArray.class));
    }

    @PostMapping("/queryVertexDetails")
    @Operation(summary = "查询tag详情")
    public JsonResult queryVertexDetails(@RequestBody GraphVertexDetails vertexDetails) {
        if (vertexDetails == null || vertexDetails.getVids().isEmpty()) {
            return ResultTool.fail();
        }
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.vertexDetails(vertexDetails), JSONArray.class));
    }

    @PostMapping("/clearSpaceAll")
    @Operation(summary = "清空数据")
    public JsonResult clearSpaceAll(@RequestBody GraphSpaceNoPage space) {
        String spaceName = space.getSpace();
        String trim = spaceName.trim();
        if (StringUtils.isBlank(trim)) {
            return ResultTool.fail();
        }
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.clearSpace(trim), JSONArray.class));
    }

    @PostMapping("/showTags")
    @Operation(summary = "查询实体标签")
    public JsonResult showTags(@RequestBody GraphSpaceNoPage space) {
        String spaceName = space.getSpace();
        String trim = spaceName.trim();
        if (StringUtils.isBlank(trim)) {
            return ResultTool.fail();
        }
        GraphShowAttribute attribute = new GraphShowAttribute();
        attribute.setSpace(spaceName);
        attribute.setAttribute(AttributeEnum.TAGS.name());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.showAttributes(attribute), JSONArray.class));
    }


    @PostMapping("/showTagAttrs")
    @Operation(summary = "查询实体属性")
    public JsonResult showTagAttrs(@RequestBody GraphShowInfo space) {
        String spaceName = space.getSpace();
        String trim = spaceName.trim();
        if (StringUtils.isBlank(trim)) {
            return ResultTool.fail();
        }
        space.setAttribute(SchemaUsedEnum.NEBULA_TAG.getDescribe());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.showAttributeInfo(space), JSONArray.class));
    }

    @PostMapping("/showTagEdge")
    @Operation(summary = "查询实体关联边")
    public JsonResult showTagEdge(@RequestBody GraphShowEdge space) {
        if (StringUtils.isBlank(space.getSpace().trim()) ||
                StringUtils.isBlank(space.getAttributeName().trim())) {
            return ResultTool.fail();
        }
        return ResultTool.success();
    }

    @PostMapping("/visualization")
    @Operation(summary = "数据可视化界面")
    public JsonResult visualization(@RequestBody GraphVertexQuery space) {
        String spaceName = space.getSpace();
        String tagName = space.getTagName();
        if (StringUtils.isBlank(spaceName.trim()) || StringUtils.isBlank(tagName.trim()) || space.getResultSize() == null) {
            return ResultTool.fail();
        }
        if (space.getAttrList().size() != space.getAttrValueList().size()) {
            return ResultTool.fail();
        }
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.queryMatchAttribute(space), JSONArray.class));
    }

    @PostMapping("/nextVertex")
    @Operation(summary = "下个节点查询")
    public JsonResult nextVertex(@RequestBody GraphEdgeQuery space) {
        String spaceName = space.getSpace();
        if (StringUtils.isBlank(spaceName.trim()) || StringUtils.isBlank(space.getVertexId().trim())) {
            return ResultTool.fail();
        }
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.queryMatchEdge(space), JSONArray.class));
    }

    @PostMapping("/queryPath")
    @Operation(summary = "两点之间路径")
    public JsonResult queryPath(@RequestBody GraphPathQuery space) {
        String spaceName = space.getSpace();
        if (StringUtils.isBlank(spaceName.trim()) || StringUtils.isBlank(space.getSrcId().trim())|| StringUtils.isBlank(space.getDstId().trim())) {
            return ResultTool.fail();
        }
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.queryPath(space), JSONArray.class));
    }

    @PostMapping("/extendedQuery")
    @Operation(summary = "扩展查询")
    public JsonResult extendedQuery(@RequestBody GraphExpand expand) {
        String spaceName = expand.getSpace();
        List<Object> vidList = expand.getVidList();
        if (StringUtils.isBlank(spaceName.trim()) || CollUtil.isEmpty(vidList)) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(spaceName);
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.expandQuery(expand,vidType), JSONArray.class));
    }

    @PostMapping("/insertTag")
    @Operation(summary = "新增节点")
    public JsonResult insertTag(@RequestBody GraphCreateVertex vertex) {
        if (StringUtils.isBlank(vertex.getSpace()) || vertex.getPointKey() == null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(vertex.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.createPoint(vertex,vidType), JSONArray.class));
    }

    @PostMapping("/deleteTag")
    @Operation(summary = "删除节点")
    public JsonResult deleteTag(@RequestBody GraphDeleteVertex vertex) {
        if (StringUtils.isBlank(vertex.getSpace()) || vertex.getVid() == null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(vertex.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.deleteVertex(vertex,vidType), JSONArray.class));
    }

    @PostMapping("/deleteTagEdge")
    @Operation(summary = "删除节点及关系")
    public JsonResult deleteTagEdge(@RequestBody GraphDeleteVertex vertex) {
        if (StringUtils.isBlank(vertex.getSpace()) || vertex.getVid() == null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(vertex.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.deleteVertexEdge(vertex,vidType), JSONArray.class));
    }

    @PostMapping("/updateTag")
    @Operation(summary = "更新节点")
    public JsonResult updateTag(@RequestBody GraphUpdateVertex vertex) {
        if (StringUtils.isBlank(vertex.getSpace()) || vertex.getPointKey() == null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(vertex.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.updateVertex(vertex,vidType), JSONArray.class));
    }

    @PostMapping("/insertEdge")
    @Operation(summary = "新增关系")
    public JsonResult insertEdge(@RequestBody GraphInsertEdge edge) {
        if (StringUtils.isBlank(edge.getSpace()) || StringUtils.isBlank(edge.getSrcVid()) || StringUtils.isBlank(edge.getDstVid())) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(edge.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.insertEdge(edge,vidType), JSONArray.class));
    }

    @PostMapping("/deleteEdge")
    @Operation(summary = "删除关系")
    public JsonResult deleteEdge(@RequestBody GraphDeleteEdge edge) {
        if (StringUtils.isBlank(edge.getSpace()) || edge.getSrcVid() != null || edge.getDstVid() != null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(edge.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.deleteEdge(edge, vidType), JSONArray.class));
    }

    @PostMapping("/updateEdge")
    @Operation(summary = "更新关系")
    public JsonResult updateEdge(@RequestBody GraphUpdateEdge edge) {
        if (StringUtils.isBlank(edge.getSpace()) || edge.getSrcVid() != null || edge.getDstVid() != null) {
            return ResultTool.fail();
        }
        String vidType = graphCommonService.getVidType(edge.getSpace());
        return ResultTool.success(graphCommonService.executeJson(NebulaUtil.updateEdge(edge, vidType), JSONArray.class));
    }
}