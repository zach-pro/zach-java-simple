package com.zach.common.config.nebula;

import com.zach.common.exception.NebulaInitException;
import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.exception.AuthFailedException;
import com.vesoft.nebula.client.graph.exception.ClientServerIncompatibleException;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.exception.NotValidConnectionException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

/**
 * @author apple
 * @Classname NebulaConfig
 * @Description nebula配置类
 */
@Slf4j
@Configuration
public class NebulaConfig {

    @Bean(destroyMethod = "close")
    public NebulaPool nebulaPool(NebulaProperties properties) throws UnknownHostException {
        NebulaPool pool = new NebulaPool();
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(properties.getMaxConnSize());
        nebulaPoolConfig.setIdleTime(properties.getIdleTime());
        boolean init = pool.init(properties.getAddress().stream().map(d -> new HostAddress(d.getHost(), d.getPort())).collect(Collectors.toList()), nebulaPoolConfig);
        if (!init){
            throw new NebulaInitException("NebulaGraph init err !");
        }
        log.info("NebulaGraph init Success ！");
        return pool;
    }

    @Bean
    public Session session(NebulaPool nebulaPool, NebulaProperties properties) throws IOErrorException, AuthFailedException, ClientServerIncompatibleException, NotValidConnectionException {
        return nebulaPool.getSession(properties.getUsername(), properties.getPassword(), properties.isReconnect());
    }
}