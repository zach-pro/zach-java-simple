package com.zach.modular.business.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zach.common.base.IdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author apple
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("example")
@Schema(description = "示例")
public class Example extends IdEntity<Example> {

    @TableField("example_code")
    @Schema(description = "示例编码")
    private String exampleCode;

    @TableField("example_desc")
    @Schema(description = "描述")
    private String exampleDesc;

    @TableField("type")
    @Schema(description = "类型")
    private String type;
}
