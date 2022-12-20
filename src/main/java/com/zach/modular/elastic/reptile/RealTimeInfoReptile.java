package com.zach.modular.elastic.reptile;

/**
 * @Classname RealTimeInfoReptile
 * @Description 爬取新闻
 */
public interface RealTimeInfoReptile {
    /**
     * 新浪爬虫
     */
    Object sinaCrawler();

    /**
     * 中国军网
     */
    Object chinaMilitaryNetwork();

}
