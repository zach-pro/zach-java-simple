package com.zach.modular.elastic.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * @Classname RealTimeInfo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealTimeInfo {
    @Schema(description = "标题")
    private String title;

    @Schema(description = "地址")
    private String newsUrl;

    @Schema(description = "正文")
    private List<String> content;

    @Schema(description = "类型")
    private String newsType;

    @Schema(description = "关键词")
    private List<String> keywords;

    @Schema(description = "发布时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateDate;

    @Schema(description = "来源")
    private String source;

    @Schema(description = "相关图片")
    private List<String> imgUrls;
}
