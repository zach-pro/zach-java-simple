package com.emas.common.base;

import cn.hutool.core.lang.id.NanoId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.junit.platform.commons.util.StringUtils;

import java.io.Serializable;

/**
 * @author by apple
 * @Classname BaseEntity
 * @Description 基类
 */
@Data
public class IdEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "实体编号（唯一标识）")
    protected String id;

    public void preInsert(){
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if(StringUtils.isBlank(getId())){
            setId(NanoId.randomNanoId());
        }
    }
}
