package com.zach.modular.nebula.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zach.common.config.nebula.NebulaConfig;
import com.zach.common.config.nebula.NebulaProperties;
import com.zach.common.exception.GraphExecuteException;
import com.zach.common.utils.nebula.NebulaUtil;
import com.zach.modular.nebula.entity.graph.GraphShowInfo;
import com.zach.modular.nebula.vo.AttributeVo;
import com.vesoft.nebula.client.graph.exception.AuthFailedException;
import com.vesoft.nebula.client.graph.exception.ClientServerIncompatibleException;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.exception.NotValidConnectionException;
import com.vesoft.nebula.client.graph.net.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * 图数据库语句执行
 * @author apple
 */
@Slf4j
@Service
public class GraphCommonService {

    Session session;
    final NebulaProperties nebulaProperties;

    public GraphCommonService(Session session, NebulaProperties nebulaProperties) {
        this.session = session;
        this.nebulaProperties = nebulaProperties;
    }

    public List executeJson(String gql, Class<?> voClass) {
        try {
            log.info("执行 executeJson 方法，gql={}", gql);
            String data;
            try {
                data = session.executeJson(gql);
            } catch (IOErrorException e) {
                session.release();
                NebulaConfig nebulaConfig = new NebulaConfig();
                session = nebulaConfig.session(nebulaConfig.nebulaPool(nebulaProperties), nebulaProperties);
                data = session.executeJson(gql);
            }
            final JSONObject jsonObject = JSON.parseObject(data);
            // 查询语句异常分析, 根据 json 返回结果解析 error 节点信息
            final JSONObject error0 = jsonObject.getJSONArray("errors").getJSONObject(0);
            final Integer code = error0.getInteger("code");
            if (code != 0) {
                throw new GraphExecuteException("execute gql error, gql: " + gql +
                    " ,code:" + code + ", message:" + error0.getString("message"));
            }
            JSONArray results = JSONUtil.parseArray(jsonObject.get("results"));
            log.info("返回结果: {}", results);
            return JSONUtil.toList(results, voClass);
        } catch (IOErrorException | AuthFailedException | ClientServerIncompatibleException | NotValidConnectionException | UnknownHostException e) {
            log.error("executeJson ql[{}] error, msg [{}]", gql, e.getMessage());
            throw new GraphExecuteException("execute gql error, gql: " + gql, e);
        }
    }

    public String getVidType(String space) {
        List<AttributeVo> spaceList = executeJson(NebulaUtil.showAttributeInfo(GraphShowInfo.builder()
                        .attribute("space").attributeName(space).space(space).build())
                , AttributeVo.class);
        AttributeVo attributeVo = spaceList.get(0);
        AttributeVo.DataBean dataBean = attributeVo.getData().get(0);
        return dataBean.getRow().get(6);
    }

}
