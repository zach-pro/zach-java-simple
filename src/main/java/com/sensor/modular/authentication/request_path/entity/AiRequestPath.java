package com.sensor.modular.authentication.request_path.entity;

import com.sensor.common.base.BaseEntity;
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
 * @since 2022-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AiRequestPath extends BaseEntity<AiRequestPath> {

    @Schema(description  = "请求路径")
    private String url;

    @Schema(description  = "路径描述")
    private String description;

}
