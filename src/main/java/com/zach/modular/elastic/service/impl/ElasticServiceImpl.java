package com.zach.modular.elastic.service.impl;

import cn.hutool.core.lang.id.NanoId;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.elastic.entity.RealTimeInfo;
import com.zach.modular.elastic.service.ElasticService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ElasticServiceImpl
 */
@Service
public class ElasticServiceImpl implements ElasticService {
    private final String INDEX_NAME = "real_time_info";

    @Qualifier("elasticSearchClient")
    private final ElasticsearchClient client;

    public ElasticServiceImpl(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public CreateIndexResponse createIndex(String index) throws IOException {
        return client.indices().create(c -> c.index(index));
    }

    @Override
    public DeleteIndexResponse deleteIndex(String index) throws IOException {
        return client.indices().delete(c -> c.index(index));
    }

    @Override
    public JsonResult createDocument(RealTimeInfo info) throws IOException {
        IndexResponse indexResponse = client.index(i -> i
                .index(INDEX_NAME)
                .id(NanoId.randomNanoId())
                .document(info));
        return ResultTool.success(indexResponse);
    }

    @Override
    public JsonResult createDocumentSpecifyId(RealTimeInfo info,String id) throws IOException {
        IndexResponse indexResponse = client.index(i -> i
                .index(INDEX_NAME)
                .id(id)
                .document(info));
        return ResultTool.success(indexResponse);
    }

    @Override
    public JsonResult updateDocument(String id, RealTimeInfo info) throws IOException {
        UpdateResponse<RealTimeInfo> realTimeInfo = client.update(u -> u
                .index(INDEX_NAME)
                .id(id)
                .doc(info), RealTimeInfo.class);
        return ResultTool.success(realTimeInfo);
    }

    @Override
    public JsonResult existDocumentTest(String id) throws IOException {
        BooleanResponse indexResponse = client.exists(e -> e.index(INDEX_NAME).id(id));
        return ResultTool.success(indexResponse);
    }

    @Override
    public JsonResult getDocument(String id) throws IOException {
        GetResponse<RealTimeInfo> getResponse = client.get(g -> g
                        .index(INDEX_NAME)
                        .id(id)
                , RealTimeInfo.class
        );
        return ResultTool.success(getResponse);
    }

    @Override
    public JsonResult deleteDocument(String id) throws IOException {
        DeleteResponse deleteResponse = client.delete(d -> d
                .index(INDEX_NAME)
                .id(id)
        );
        return ResultTool.success(deleteResponse);
    }

    @Override
    public JsonResult bulkDocument(List<RealTimeInfo> info) throws IOException {
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        //遍历添加到bulk中
        for(RealTimeInfo realTimeInfo : info){
            bulkOperationArrayList.add(BulkOperation.of(o->o.index(i->i.document(realTimeInfo))));
        }
        BulkResponse bulkResponse = client.bulk(b -> b.index(INDEX_NAME)
                .operations(bulkOperationArrayList));
        return ResultTool.success(bulkResponse);
    }

    @Override
    public List<RealTimeInfo> searchDocument(String text) throws IOException {
        List<RealTimeInfo> list = new ArrayList<>();
        // 执行查询
        SearchResponse<RealTimeInfo> searchResponse = client.search(srBuilder -> srBuilder
                        .index(INDEX_NAME)
                        //.from(1)
                        //.size(10)
                        // MultiMatch 查找：对输入内容先分词再查询。
                        .query(queryBuilder -> queryBuilder
                                .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                        .fields("title", "content")
                                        .query(text)
                                        .operator(Operator.Or))
                        )
                , RealTimeInfo.class);
        for (Hit<RealTimeInfo> hit : searchResponse.hits().hits()) {
            list.add(hit.source());
        }
        return list;
    }

    @Override
    public List<RealTimeInfo> searchTop10() throws IOException {
        List<RealTimeInfo> list = new ArrayList<>();
        // 执行查询
        SearchResponse<RealTimeInfo> searchResponse = client.search(srBuilder -> srBuilder
                .index(INDEX_NAME)
                // .from(0)
                 .size(10)
                //指定排序
                .sort(sortOptionsBuilder -> sortOptionsBuilder
                        .field(fieldSortBuilder -> fieldSortBuilder
                                .field("updateDate").order(SortOrder.Desc))), RealTimeInfo.class);

        for (Hit<RealTimeInfo> hit : searchResponse.hits().hits()) {
            list.add(hit.source());
        }
        return list;
    }

    @Override
    public JsonResult searchHighlight(String text) throws IOException {
        Map<String, Object> map = new HashMap<>(2);
        List<RealTimeInfo> list = new ArrayList<>();
        List<Map<String, List<String>>> highlightList = new ArrayList<>();
        // 执行查询
        SearchResponse<RealTimeInfo> searchResponse = client.search(srBuilder -> srBuilder
                        .index(INDEX_NAME)
                        // MultiMatch 查找：对输入内容先分词再查询。
                        .query(queryBuilder -> queryBuilder
                                .multiMatch(multiMatchQueryBuilder -> multiMatchQueryBuilder
                                        .fields("title", "content")
                                        .query(text)
                                        .operator(Operator.Or))
                        )
                        // 高亮查询
                        .highlight(highlightBuilder -> highlightBuilder
                                .preTags("<font color='red'>")
                                .postTags("</font>")
                                .requireFieldMatch(false) //多字段时，需要设置为false
                                .fields("title", highlightFieldBuilder -> highlightFieldBuilder)
                                .fields("content", highlightFieldBuilder -> highlightFieldBuilder)
                        )
                , RealTimeInfo.class);
        for (Hit<RealTimeInfo> hit : searchResponse.hits().hits()) {
            list.add(hit.source());
            highlightList.add(hit.highlight());
        }
        map.put("list",list);
        map.put("highlightList",highlightList);
        return ResultTool.success(map);
    }


}
