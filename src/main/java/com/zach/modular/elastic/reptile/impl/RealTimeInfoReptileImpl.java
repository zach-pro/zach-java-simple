package com.zach.modular.elastic.reptile.impl;

import com.zach.modular.elastic.entity.RealTimeInfo;
import com.zach.modular.elastic.reptile.RealTimeInfoReptile;
import com.zach.modular.elastic.service.impl.ElasticServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Classname RealTimeInfoReptile
 * @Description 爬取新闻
 */
@Service
public class RealTimeInfoReptileImpl implements RealTimeInfoReptile {
    private final ElasticServiceImpl elasticService;

    public RealTimeInfoReptileImpl(ElasticServiceImpl elasticService) {
        this.elasticService = elasticService;
    }

    @Override
    public Object sinaCrawler() {
        int maxPage = 25;
        String[] cid =  {"57918","57919"};
        String[] cname =  {"中国军情","国际军情"};
        //爬取武器概述
         String path = "https://mil.news.sina.com.cn/roll/index.d.html?cid=%s&page=%s";
        //武器列表
        try {
            for (int i = 0; i < cid.length; i++) {
                String newsType = cname[i];
                for (int j = 1; j <= maxPage; j++) {
                    URL url = new URL(String.format(path, cid[i], j));
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    //获取输入流
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    //读流
                    String line = null;
                    StringBuilder sb = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    Document doc = Jsoup.parse(String.valueOf(sb));
                    Elements elements = doc.select("body > div > div.row.col2.clearfix > div.main > div.fixList > ul > li > a");
                    for (Element element : elements) {
                        String title = element.text();
                        String href = element.attr("href");
                        String id = UUID.nameUUIDFromBytes((href).getBytes()).toString();

                        if (href.contains("video")) {
                            continue;
                        }
                        URL urlInfo = new URL(href);
                        URLConnection urlConnectionInfo = urlInfo.openConnection();
                        urlConnectionInfo.connect();
                        //获取输入流
                        BufferedReader bufferedInfoReader = new BufferedReader(new InputStreamReader(urlConnectionInfo.getInputStream()));
                        //读流
                        String lineInfo = null;
                        StringBuilder sbInfo = new StringBuilder();
                        while ((lineInfo = bufferedInfoReader.readLine()) != null) {
                            sbInfo.append(lineInfo);
                        }
                        RealTimeInfo newsInfo = new RealTimeInfo();
                        newsInfo.setTitle(title);
                        newsInfo.setNewsUrl(href);
                        newsInfo.setNewsType(newsType);

                        Document docInfo = Jsoup.parse(String.valueOf(sbInfo));
                        Elements select = docInfo.select("body > div.main-content.w1240 > h1");
                        String text = select.text();
                        if (!text.isEmpty()) {
                            // 发布时间
                            Elements upDate = docInfo.select("#top_bar > div > div.date-source > span");
                            String dateText = upDate.text();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                            Date date = format.parse(dateText);
                            newsInfo.setUpdateDate(date);
                            newsInfo.setCreateTime(date);
                            // 来源
                            Elements source = docInfo.select("#top_bar > div > div.date-source > a");
                            newsInfo.setSource(source.text());
                            // 正文
                            List<String> contents = new ArrayList<>();
                            List<String> imgList = new ArrayList<>();
                            Elements contentElements = docInfo.select("#article");
                            for (Element contentElement : contentElements) {
                                List<Node> nodes = contentElement.childNodes();
                                for (Node node : nodes) {
                                    if (node.getClass() == Element.class) {
                                        Element ele = (Element) node;
                                        if ("p".equals(ele.tagName())) {
                                            contents.add(ele.text());
                                        }
                                        if ("div".equals(ele.tagName())) {
                                            List<Node> nodeImg = ele.childNodes();
                                            for (Node img : nodeImg) {
                                                if (img.getClass() == Element.class) {
                                                    Element eleImg = (Element) img;
                                                    if ("img".equals(eleImg.tagName())) {
                                                        String src = eleImg.attr("src");
                                                        String imgUrl = "http:" + src;
                                                        imgList.add(imgUrl);
                                                        contents.add("<img>");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            newsInfo.setContent(contents);
                            newsInfo.setImgUrls(imgList);
                            // 关键词
                            List<String> keys = new ArrayList<>();
                            Elements keywords = docInfo.select("#article-bottom > div.keywords");
                            for (Element keyword : keywords) {
                                List<Node> nodes = keyword.childNodes();
                                for (Node node : nodes) {
                                    if (node.getClass() == Element.class) {
                                        Element ele = (Element) node;
                                        if ("a".equals(ele.tagName())) {
                                            keys.add(ele.text());
                                        }
                                    }
                                }
                            }
                            newsInfo.setKeywords(keys);
                            elasticService.createDocumentSpecifyId(newsInfo,id);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public Object chinaMilitaryNetwork() {
        String[] cid = {"105013", "61571", "62811"};
        String[] cname =  {"我军装备","兵器动态","外军兵器"};
        //爬取武器概述
        String path = "http://81.cn/bqtd/node_%s%s.htm";
        //武器列表
        try {
            for (int i = 0; i < cid.length; i++) {
                String newsType = cname[i];
                URL url = new URL(String.format(path, cid[i], ""));
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                //获取输入流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                //读流
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                Document doc = Jsoup.parse(String.valueOf(sb));
                Elements pageCount = doc.select(".page");
                for (int j = 0; j < pageCount.size(); j++) {
                    String currentPage = pageCount.get(j).text();
                    if ("1".equals(currentPage)) {
                        currentPage = "";
                    }else{
                        currentPage = "_" + currentPage;
                    }
                    URL currentUrl = new URL(String.format(path, cid[i], currentPage));
                    URLConnection currentUrlConnection = currentUrl.openConnection();
                    currentUrlConnection.connect();
                    //获取输入流
                    BufferedReader currentBufferedReader = new BufferedReader(new InputStreamReader(currentUrlConnection.getInputStream()));
                    //读流
                    String currentLine = null;
                    StringBuilder currentSb = new StringBuilder();
                    while ((currentLine = currentBufferedReader.readLine()) != null) {
                        currentSb.append(currentLine);
                    }
                    Document currentDoc = Jsoup.parse(String.valueOf(currentSb));
                    Elements elements = currentDoc.select("#main-news-list > li > a");
                    for (Element element : elements) {
                        String href = element.attr("href").replace("../","");
                        if (href.contains("http://tv.81.cn/")) {
                            continue;
                        }
                        if (href.contains("wjsm")) {
                            href = "http://81.cn/" + href;
                        }else if (!href.contains("http://81.cn/bqtd/")) {
                            href = "http://81.cn/bqtd/" + href;
                        }
                        String id = UUID.nameUUIDFromBytes((href).getBytes()).toString();
                        URL urlInfo = new URL(href);

                        URLConnection urlConnectionInfo = urlInfo.openConnection();
                        urlConnectionInfo.connect();
                        //获取输入流
                        BufferedReader bufferedInfoReader = new BufferedReader(new InputStreamReader(urlConnectionInfo.getInputStream()));
                        //读流
                        String lineInfo = null;
                        StringBuilder sbInfo = new StringBuilder();
                        while ((lineInfo = bufferedInfoReader.readLine()) != null) {
                            sbInfo.append(lineInfo);
                        }

                        RealTimeInfo newsInfo = new RealTimeInfo();
                        newsInfo.setNewsUrl(href);
                        newsInfo.setNewsType(newsType);

                        // 标题
                        Document docInfo = Jsoup.parse(String.valueOf(sbInfo));
                        Elements select = docInfo.select("body > div.container.m-t > div > div.content > div.article-header > h1");
                        String text = select.text();
                        newsInfo.setTitle(text);
                        if (!text.isEmpty()) {
                            // 发布时间
                            Elements upDate = docInfo.select("body > div.container.m-t > div > div.content > div.article-header > div.info > div > small > i.time");
                            String dateText = upDate.text();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            Date date = format.parse(dateText);
                            newsInfo.setUpdateDate(date);
                            newsInfo.setCreateTime(date);
                            // 来源
                            Elements source = docInfo.select("body > div.container.m-t > div > div.content > div.article-header > div.info > span:nth-child(1)");
                            newsInfo.setSource(source.text().replace("来源：","").trim());
                            // 正文
                            List<String> contents = new ArrayList<>();
                            List<String> imgList = new ArrayList<>();
                            Elements contentElements = docInfo.select("#article-content");
                            for (Element contentElement : contentElements) {
                                List<Node> nodes = contentElement.childNodes();
                                for (Node node : nodes) {
                                    if (node.getClass() == Element.class) {
                                        Element ele = (Element) node;
                                        if ("p".equals(ele.tagName())) {
                                            contents.add(ele.text());
                                        }
                                        if ("center".equals(ele.tagName())) {
                                            List<Node> nodeImg = ele.childNodes();
                                            for (Node img : nodeImg) {
                                                if (img.getClass() == Element.class) {
                                                    Element eleImg = (Element) img;
                                                    if ("img".equals(eleImg.tagName())) {
                                                        String src = eleImg.attr("src").replace("../../","");
                                                        String imgUrl = "http://81.cn/bqtd/" + src;
                                                        imgList.add(imgUrl);
                                                        contents.add("<img>");
                                                    }
                                                    if ("font".equals(eleImg.tagName())) {
                                                        List<Node> fontImg = eleImg.childNodes();
                                                        for (Node font : fontImg) {
                                                            if (font.getClass() == Element.class) {
                                                                Element img2 = (Element) font;
                                                                if ("img".equals(img2.tagName())) {
                                                                    String src = img2.attr("src").replace("../../","");
                                                                    String imgUrl = "http://81.cn/bqtd/" + src;
                                                                    imgList.add(imgUrl);
                                                                    contents.add("<img>");
                                                                }
                                                                if ("span".equals(img2.tagName())) {
                                                                    String span = img2.text();
                                                                    imgList.add(span);
                                                                    contents.add("<span>");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if ("span".equals(ele.tagName())) {
                                            List<Node> nodeImg = ele.childNodes();
                                            for (Node img : nodeImg) {
                                                if (img.getClass() == Element.class) {
                                                    Element eleImg = (Element) img;
                                                    if ("center".equals(eleImg.tagName())) {
                                                        List<Node> nodeImg2 = eleImg.childNodes();
                                                        for (Node nodeNode : nodeImg2) {
                                                            if (nodeNode.getClass() == Element.class) {
                                                                Element eleImg2 = (Element) nodeNode;
                                                                if ("img".equals(eleImg2.tagName())) {
                                                                    String src = eleImg2.attr("src").replace("../../","");
                                                                    String imgUrl = "http://81.cn/bqtd/" + src;
                                                                    imgList.add(imgUrl);
                                                                    contents.add("<img>");
                                                                }
                                                                if ("font".equals(eleImg2.tagName())) {
                                                                    List<Node> fontImg = ele.childNodes();
                                                                    for (Node font : fontImg) {
                                                                        if (font.getClass() == Element.class) {
                                                                            Element img2 = (Element) font;
                                                                            if ("img".equals(img2.tagName())) {
                                                                                String src = img2.attr("src").replace("../../","");
                                                                                String imgUrl = "http://81.cn/bqtd/" + src;
                                                                                imgList.add(imgUrl);
                                                                                contents.add("<img>");
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            newsInfo.setContent(contents);
                            newsInfo.setImgUrls(imgList);
                            // 关键词
                            List<String> keys = new ArrayList<>();
                            newsInfo.setKeywords(keys);
                            elasticService.createDocumentSpecifyId(newsInfo,id);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
