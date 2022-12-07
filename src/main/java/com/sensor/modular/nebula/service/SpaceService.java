package com.sensor.modular.nebula.service;


import cn.hutool.core.collection.CollectionUtil;
import com.sensor.common.constant.AttributeEnum;
import com.sensor.common.utils.nebula.NebulaUtil;
import com.sensor.modular.nebula.entity.graph.GraphShowAttribute;
import com.sensor.modular.nebula.vo.AttributeVo;
import com.sensor.modular.nebula.vo.DetailSpace;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apple
 * @Descriptin: 图空间
 * @ClassName: SpaceService
 */
@Service
public class SpaceService {

    final GraphCommonService graphCommonService;
    public SpaceService(GraphCommonService graphCommonService) {
        this.graphCommonService = graphCommonService;
    }

    public List<DetailSpace> detailSpace(GraphShowAttribute graphShowAttribute) {
        // 所有图空间
        List<AttributeVo> spacesList = graphCommonService.executeJson(NebulaUtil.showAttributes(graphShowAttribute), AttributeVo.class);
        AttributeVo attributeVo1 = spacesList.get(0);

        List<DetailSpace> detailSpaceList = CollectionUtil.newArrayList();

        DetailSpace detailSpace = null;
        for (AttributeVo.DataBean datum : attributeVo1.getData()) {
            int tagsNum = 0;
            int edgesNum = 0;
            int tag = 0;
            detailSpace = new DetailSpace();
            // 查询tgas/edges
            String spaceName = datum.getRow().get(0);
            graphShowAttribute.setSpace(spaceName);
            graphShowAttribute.setAttribute(AttributeEnum.TAGS.name());
            List<AttributeVo> tagsList = graphCommonService.executeJson(NebulaUtil.showAttributes(graphShowAttribute), AttributeVo.class);

            AttributeVo attributeVoTag = tagsList.get(0);
            for (AttributeVo.DataBean attributeVoTagDatum : attributeVoTag.getData()) {
                tagsNum += attributeVoTagDatum.getRow().size();
            }
            graphShowAttribute.setAttribute(AttributeEnum.EDGES.name());
            List<AttributeVo> edgesList = graphCommonService.executeJson(NebulaUtil.showAttributes(graphShowAttribute), AttributeVo.class);
            for (AttributeVo.DataBean dataBean : edgesList.get(0).getData()) {
                edgesNum += dataBean.getRow().size();
            }
            detailSpace.setSpace(spaceName);
            detailSpace.setTagsNum(tagsNum);
            detailSpace.setEdgesNum(edgesNum);
            detailSpaceList.add(detailSpace);
        }

        return detailSpaceList;
    }
}
