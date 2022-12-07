package com.emas.modular.emas.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.emas.common.base.IdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author apple
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emas")
@Schema(description = "示例")
public class Emas extends IdEntity<Emas> {

    @TableField("mgt_org_code")
    @Schema(description = "mgt_org_code")
    private String mgtOrgCode;

    @TableField("mgt_org_name")
    @Schema(description = "mgt_org_name")
    private String mgtOrgName;

    @TableField("dist_lv")
    @Schema(description = "dist_lv")
    private String distLv;

    @TableField("cust_num")
    @Schema(description = "cust_num")
    private Integer custNum;

    @TableField("tmnl_num")
    @Schema(description = "tmnl_num")
    private Integer tmnlNum;

    @TableField("yn_flag")
    @Schema(description = "yn_flag")
    private String ynFlag;

    @TableField("meter_num")
    @Schema(description = "meter_num")
    private Integer meterNum;


}
