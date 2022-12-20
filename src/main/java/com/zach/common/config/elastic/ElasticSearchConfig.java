package com.zach.common.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

/**
 * @Classname ElasticSearchConfig
 * @Description elastic配置类
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String hosts;

    @Value("${spring.elasticsearch.username}")
    private String userName;

    @Value("${spring.elasticsearch.password}")
    private String passWord;


    @Bean
    @Lazy
    public ElasticsearchClient elasticSearchClient(){
        HttpHost[] httpHosts = toHttpHost();
        // 无验证信息
        RestClientBuilder builder = RestClient.builder(httpHosts);
        builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(60000).setConnectTimeout(5000));
        RestClient restClient = builder.build();
        // 有验证信息
        //final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
        //RestClientBuilder builder = RestClient.builder(httpHosts);
        //builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(60000).setConnectTimeout(5000));
        //builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        //RestClient restClient = builder.build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        // And create the API client
        return new ElasticsearchClient(transport);
    }

    /**
     * 异步方式
     *
     * @return
     */
    @Bean
    public ElasticsearchAsyncClient elasticSearchAsyncClient() {
        HttpHost[] httpHosts = toHttpHost();
        RestClient restClient = RestClient.builder(httpHosts).build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchAsyncClient(transport);

    }

    private HttpHost[] toHttpHost() {
        if (!StringUtils.hasLength(hosts)) {
            throw new RuntimeException("invalid elasticsearch configuration. elasticsearch.hosts不能为空！");
        }

        // 多个IP逗号隔开
        String[] hostArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        for (int i = 0; i < hostArray.length; i++) {
            String[] strings = hostArray[i].split(":");
            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
            httpHosts[i] = httpHost;
        }

        return httpHosts;
    }

}