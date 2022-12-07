package com.emas.modular.emas.request;

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
@Schema(description = "查询批量添加入参")
public class EmasInsertBatchRequest {

    @Schema(description = "执行的sql")
    private String[] sql;

    @Schema(description = "账号名称")
    private String accountId;

    @Schema(description = "数据库编码")
    private String mgtOrgCode;

    @Schema(description = "是否去省库执行")
    private String forceToZj;

    @Schema(description = "Ip地址")
    private String reqIp;

    @Schema(description = "业务层名称里面的sql的id")
    private String sqlId;
}
