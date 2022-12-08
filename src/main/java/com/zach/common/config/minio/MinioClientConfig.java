package com.zach.common.config.minio;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author by apple
 * @Classname MinioClientConfig
 * @Description minio配置类
 * @Date 2022/9/7 19:29
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioClientConfig {

    private String endPoint;
    private String accessKey;
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
    }

}