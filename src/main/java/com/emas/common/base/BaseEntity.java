package com.emas.common.base;

import cn.hutool.core.lang.id.NanoId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.emas.common.config.Global;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.junit.platform.commons.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author by apple
 * @Classname BaseEntity
 * @Description 基类
 */
@Data
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "实体编号（唯一标识）")
    protected String id;

    @JsonIgnore
    @Schema(description = "创建用户Id")
    protected String createUser;

    @JsonIgnore
    @Schema(description = "创建时间")
    private String createTime;

    /**
     * 全局变量对象
     */
    @JsonIgnore
    public Global getGlobal() {
        return Global.getInstance();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createUser, createTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null != this.getId() && this.getId().equals(that.getId());
    }

    public void preInsert(){
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if(StringUtils.isBlank(getId())){
            setId(NanoId.randomNanoId());
        }
        this.createTime = LocalDateTime.now() + "";
    }

}
