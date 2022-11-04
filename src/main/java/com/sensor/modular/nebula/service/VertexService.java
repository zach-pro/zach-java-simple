package com.sensor.modular.nebula.service;


import com.sensor.common.utils.nebula.NebulaUtil;
import com.sensor.modular.nebula.entity.NebulaVertexJsonResult;
import com.sensor.modular.nebula.entity.graph.GraphExpand;
import com.sensor.modular.nebula.entity.graph.GraphVertexTatAttributeQuery;
import com.sensor.modular.nebula.entity.graph.GraphVertexTatsQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apple
 * @Descriptin: 点实现类
 * @ClassName: VertexService
 */
@Service
@Slf4j
public class VertexService {

    GraphCommonService graphCommonService;

    public VertexService(GraphCommonService graphCommonService) {
        this.graphCommonService = graphCommonService;
    }


    public List<NebulaVertexJsonResult> vertexList(String space) {
        return graphCommonService.executeJson(NebulaUtil.queryMatch(space), NebulaVertexJsonResult.class);
    }

    public List<NebulaVertexJsonResult> vertexExpandQuery(GraphExpand graphExpand) {
        String vidType = graphCommonService.getVidType(graphExpand.getSpace());
        return graphCommonService.executeJson(NebulaUtil.expandQuery(graphExpand,vidType), NebulaVertexJsonResult.class);
    }

    public List<NebulaVertexJsonResult> vertexTagsQuery(GraphVertexTatsQuery graphVertexTatsQuery) {
        String vidType = graphCommonService.getVidType(graphVertexTatsQuery.getSpace());
        return graphCommonService.executeJson(NebulaUtil.vertexTagsQuery(graphVertexTatsQuery,vidType), NebulaVertexJsonResult.class);
    }

    public List<NebulaVertexJsonResult> vertexTagAttributeQuery(GraphVertexTatAttributeQuery graphVertexTatAttributeQuery) {
        return graphCommonService.executeJson(NebulaUtil.vertexTagAttributeQuery(graphVertexTatAttributeQuery), NebulaVertexJsonResult.class);
    }
}
