package com.zach.modular.authentication.request_path.entity;

import com.zach.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 请求路径
 * </p>
 *
 * @author apple
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRequestPath extends BaseEntity<AiRequestPath> {

    @Schema(description  = "请求路径")
    private String url;

    @Schema(description  = "标签组")
    private String tagName;

    @Schema(description  = "下标值")
    private Integer tagRank;

    @Schema(description  = "路径描述")
    private String description;

}
