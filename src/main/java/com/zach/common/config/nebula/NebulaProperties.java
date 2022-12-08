package com.zach.common.config.nebula;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author apple
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "nebula")
public class NebulaProperties {
    private List<NebulaAddress> address;
    private String username;
    private String password;
    private Integer idleTime;
    private boolean reconnect;
    private Integer maxConnSize;
}
