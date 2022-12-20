package com.zach.modular.elastic.service;

import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import com.zach.common.vo.JsonResult;
import com.zach.modular.elastic.entity.RealTimeInfo;

import java.io.IOException;
import java.util.List;

/**
 * @Classname ElasticService
 */
public interface ElasticService {
    /**
     * 创建索引
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    CreateIndexResponse createIndex(String index) throws IOException;
    /**
     * 删除索引
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    DeleteIndexResponse deleteIndex(String index) throws IOException;
    /**
     * 创建文档
     * @param info
     * @return
     * @throws IOException
     */
    JsonResult createDocument(RealTimeInfo info) throws IOException;    /**
     * 创建文档
     * @param info
     * @return
     * @throws IOException
     */
    JsonResult createDocumentSpecifyId(RealTimeInfo info,String id) throws IOException;

    /**
     * 更新文档
     * @param id
     * @param info
     * @return
     * @throws IOException
     */
    JsonResult updateDocument(String id, RealTimeInfo info) throws IOException;
    /**
     * 判断文档是否存在
     * @param id
     * @return
     * @throws IOException
     */
    JsonResult existDocumentTest(String id) throws IOException;
    /**
     * 获取文档
     * @param id
     * @return
     * @throws IOException
     */
    JsonResult getDocument(String id) throws IOException;
    /**
     * 删除文档
     * @param id
     * @return
     * @throws IOException
     */
    JsonResult deleteDocument(String id) throws IOException;

    /**
     * 批量添加文档
     * @param info
     * @return
     * @throws IOException
     */
    JsonResult bulkDocument(List<RealTimeInfo> info) throws IOException;

    /**
     * 搜索
     * @param text
     * @return
     * @throws IOException
     */
    List<RealTimeInfo> searchDocument(String text) throws IOException;

    /**
     * 搜索最新Top10
     * @return
     * @throws IOException
     */
    List<RealTimeInfo> searchTop10() throws IOException;
    /**
     * 高亮查询
     * @param text
     * @return
     * @throws IOException
     */
    JsonResult searchHighlight(String text) throws IOException;

}
