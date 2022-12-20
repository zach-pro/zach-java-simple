package com.zach.modular.elastic.controller;

import com.zach.modular.elastic.reptile.impl.RealTimeInfoReptileImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author apple
 * 爬虫类
 */
@SpringBootTest
@Slf4j
public class ReptileTest {

    @Autowired
    private RealTimeInfoReptileImpl realTimeInfoReptile;

    @Test
    public void sinaCrawler() {
        realTimeInfoReptile.sinaCrawler();
    }

    @Test
    public void chinaMilitaryNetwork() {
        realTimeInfoReptile.chinaMilitaryNetwork();
    }

}