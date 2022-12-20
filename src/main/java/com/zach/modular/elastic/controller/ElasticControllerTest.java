package com.zach.modular.elastic.controller;

import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.zach.common.vo.JsonResult;
import com.zach.modular.elastic.entity.RealTimeInfo;
import com.zach.modular.elastic.service.impl.ElasticServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author apple
 * 请求测试类
 */
@SpringBootTest
@Slf4j
public class ElasticControllerTest {

    @Autowired
    private ElasticServiceImpl elasticService;

    @Test
    public void createIndex() throws IOException {
        String indexName = "real_time_info";
        elasticService.deleteIndex(indexName);
        CreateIndexResponse index = elasticService.createIndex(indexName);
        log.error(index.toString());
    }

    @Test
    public void createDocument() throws IOException {
        JsonResult realTimeInfo = elasticService.createDocument(new RealTimeInfo("乌克兰宣布国有化马达西奇 中企回应","https://www.baidu.com/s?wd=%E4%B9%8C%E5%85%8B%E5%85%B0%E5%AE%A3%E5%B8%83%E5%9B%BD%E6%9C%89%E5%8C%96%E9%A9%AC%E8%BE%BE%E8%A5%BF%E5%A5%87%20%E4%B8%AD%E4%BC%81%E5%9B%9E%E5%BA%94&rsv_idx=2&tn=baiduhome_pg&usm=1&ie=utf-8&rsv_pq=e2881fe2000803de&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=a754GioGRPfQ7Gx4tTsUJgwQpYaP4z8MdS5NDYOWOch7wu9BOzVKXtdKWCQIWR8dDVK1&rqid=e2881fe2000803de&rsf=ffdf6d417ddfe95aba7bc700b1116903_1_15_2&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        log.error(realTimeInfo.toString());
    }

    @Test
    public void updateDocument() throws IOException {
        JsonResult realTimeInfo = elasticService.updateDocument("3MvjI_AofDHPp3ZmaVhSD",new RealTimeInfo("习近平向世界互联网大会致贺信","https://www.baidu.com/s?wd=%E4%B9%A0%E8%BF%91%E5%B9%B3%E5%90%91%E4%B8%96%E7%95%8C%E4%BA%92%E8%81%94%E7%BD%91%E5%A4%A7%E4%BC%9A%E8%87%B4%E8%B4%BA%E4%BF%A1&rsv_idx=2&tn=baiduhome_pg&usm=1&ie=utf-8&rsv_pq=e2881fe2000803de&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=1478gP4YwKqwemDrDGolGKCgpHYz7CFUa2VFJ215OYA2L44smwcv6tigmbYxIxkt%2BSWd&rqid=e2881fe2000803de&rsf=ffdf6d417ddfe95aba7bc700b1116903_1_15_1&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        log.error(realTimeInfo.toString());
    }

    @Test
    public void existDocumentTest() throws IOException {
        JsonResult realTimeInfo = elasticService.existDocumentTest("3MvjI_AofDHPp3ZmaVhSD");
        log.error(realTimeInfo.toString());
    }

    @Test
    public void getDocument() throws IOException {
        JsonResult realTimeInfo = elasticService.getDocument("3MvjI_AofDHPp3ZmaVhSD");
        log.error(realTimeInfo.toString());
    }

    @Test
    public void deleteDocument() throws IOException {
        JsonResult realTimeInfo = elasticService.deleteDocument("rT-yKBebRSVg9ijWsErlg");
        log.error(realTimeInfo.toString());
    }

    @Test
    public void bulkDocument() throws IOException {
        List<RealTimeInfo> list = new ArrayList<>();
        list.add(new RealTimeInfo("乌克兰宣布国有化马达西奇 中企回应","https://www.baidu.com/s?wd=%E4%B9%8C%E5%85%8B%E5%85%B0%E5%AE%A3%E5%B8%83%E5%9B%BD%E6%9C%89%E5%8C%96%E9%A9%AC%E8%BE%BE%E8%A5%BF%E5%A5%87%20%E4%B8%AD%E4%BC%81%E5%9B%9E%E5%BA%94&rsv_idx=2&tn=baiduhome_pg&usm=1&ie=utf-8&rsv_pq=e2881fe2000803de&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=a754GioGRPfQ7Gx4tTsUJgwQpYaP4z8MdS5NDYOWOch7wu9BOzVKXtdKWCQIWR8dDVK1&rqid=e2881fe2000803de&rsf=ffdf6d417ddfe95aba7bc700b1116903_1_15_2&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("官宣的房地产支持资金已超万亿","https://www.baidu.com/s?wd=%E5%AE%98%E5%AE%A3%E7%9A%84%E6%88%BF%E5%9C%B0%E4%BA%A7%E6%94%AF%E6%8C%81%E8%B5%84%E9%87%91%E5%B7%B2%E8%B6%85%E4%B8%87%E4%BA%BF&rsv_idx=2&tn=baiduhome_pg&usm=1&ie=utf-8&rsv_pq=e2881fe2000803de&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=a754GioGRPfQ7Gx4tTsUJgwQpYaP4z8MdS5NDYOWOch7wu9BOzVKXtdKWCQIWR8dDVK1&rqid=e2881fe2000803de&rsf=ffdf6d417ddfe95aba7bc700b1116903_1_15_3&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("众多“黑科技”齐聚进博会","https://www.baidu.com/s?wd=%E4%BC%97%E5%A4%9A%E2%80%9C%E9%BB%91%E7%A7%91%E6%8A%80%E2%80%9D%E9%BD%90%E8%81%9A%E8%BF%9B%E5%8D%9A%E4%BC%9A&rsv_idx=2&tn=baiduhome_pg&usm=1&ie=utf-8&rsv_pq=e2881fe2000803de&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=a754GioGRPfQ7Gx4tTsUJgwQpYaP4z8MdS5NDYOWOch7wu9BOzVKXtdKWCQIWR8dDVK1&rqid=e2881fe2000803de&rsf=ffdf6d417ddfe95aba7bc700b1116903_1_15_4&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("共建网络世界共创数字未来","https://www.baidu.com/s?wd=%E5%85%B1%E5%BB%BA%E7%BD%91%E7%BB%9C%E4%B8%96%E7%95%8C%E5%85%B1%E5%88%9B%E6%95%B0%E5%AD%97%E6%9C%AA%E6%9D%A5&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_1&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("乌克兰宣布国有化马达西奇 中企回应","https://www.baidu.com/s?wd=%E4%B9%8C%E5%85%8B%E5%85%B0%E5%AE%A3%E5%B8%83%E5%9B%BD%E6%9C%89%E5%8C%96%E9%A9%AC%E8%BE%BE%E8%A5%BF%E5%A5%87%20%E4%B8%AD%E4%BC%81%E5%9B%9E%E5%BA%94&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_2&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("学生隔离期下楼被开除学籍?官方回应","https://www.baidu.com/s?wd=%E5%AD%A6%E7%94%9F%E9%9A%94%E7%A6%BB%E6%9C%9F%E4%B8%8B%E6%A5%BC%E8%A2%AB%E5%BC%80%E9%99%A4%E5%AD%A6%E7%B1%8D%3F%E5%AE%98%E6%96%B9%E5%9B%9E%E5%BA%94&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_3&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("在诗画江南看见数字未来","https://www.baidu.com/s?wd=%E5%9C%A8%E8%AF%97%E7%94%BB%E6%B1%9F%E5%8D%97%E7%9C%8B%E8%A7%81%E6%95%B0%E5%AD%97%E6%9C%AA%E6%9D%A5&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_4&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("广州8天累计感染破万","https://www.baidu.com/s?wd=%E5%B9%BF%E5%B7%9E8%E5%A4%A9%E7%B4%AF%E8%AE%A1%E6%84%9F%E6%9F%93%E7%A0%B4%E4%B8%87&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_5&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        list.add(new RealTimeInfo("老人住自己房子每月交6300元房租","https://www.baidu.com/s?wd=%E8%80%81%E4%BA%BA%E4%BD%8F%E8%87%AA%E5%B7%B1%E6%88%BF%E5%AD%90%E6%AF%8F%E6%9C%88%E4%BA%A46300%E5%85%83%E6%88%BF%E7%A7%9F&usm=1&ie=utf-8&rsv_pq=b8df284d000b7977&oq=%E7%99%BE%E5%BA%A6%E6%96%B0%E9%97%BB&rsv_t=6febkdfB7WvCKpJt0pHfm7Vj5%2Fvz1GEOxEsVR3cJKY%2Fn%2FaUPJlpRCSex628&rqid=b8df284d000b7977&rsf=95f83bf5f01f78b99735fb3ac93a6aa2_1_15_6&rsv_dl=0_right_fyb_pchot_20811&sa=0_right_fyb_pchot_20811",new ArrayList<>(),"",new ArrayList<>(),new Date(),new Date(),"百度新闻",new ArrayList<>()));
        JsonResult realTimeInfo = elasticService.bulkDocument(list);
        log.error(realTimeInfo.toString());
    }


    @Test
    public void searchDocument() throws Exception {
        List<RealTimeInfo> list = elasticService.searchDocument("老人");
        log.error(list.toString());
    }

    @Test
    public void searchTop10() throws Exception {
        List<RealTimeInfo> list = elasticService.searchTop10();
        log.error(list.toString());
    }

    @Test
    public void searchHighlight() throws Exception {
        JsonResult realTimeInfo = elasticService.searchHighlight("天气");
        log.error(realTimeInfo.toString());
    }

}