package com.zach.modular.authentication.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zach.common.base.BaseEntity;
import com.zach.modular.authentication.user.enums.EnabledEnum;
import com.zach.modular.authentication.user.enums.LockedEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author apple
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class AiUser extends BaseEntity<AiUser> {
    @Schema(description = "账号")
    private String account;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "账号状态: 1启用 2禁用")
    private EnabledEnum enabled;

    @Schema(description = "手机号")
    private String iphone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "账号锁定: 1否 2是")
    private LockedEnum accountNotLocked;

    @Schema(description = "公司名称")
    private String corporateName;

    @Schema(description = "用途")
    private String purpose;

    @JsonIgnore
    @Schema(description = "上一次登录时间")
    private String lastLoginTime;

    @Schema(description = "所属角色id")
    @TableField(exist = false)
    private String roleId;

    public AiUser(String account, String password, EnabledEnum enabled, LockedEnum accountNotLocked) {
        this.account = account;
        this.password = password;
        this.enabled = enabled;
        this.accountNotLocked = accountNotLocked;
    }

    /**
     * 项目是否启用
     */
    public boolean selEnabled(){
        if (EnabledEnum.ENABLE.equals(this.enabled)) {
            return true;
        }
        return false;
    }

    /**
     * 项目是否启用
     */
    public static int transformationEnabled(boolean flag){
        if (flag) {
            return EnabledEnum.ENABLE.getValue();
        }
        return EnabledEnum.DISABLE.getValue();
    }

    /**
     * 是否锁定
     */
    public boolean selAccountNotLocked(){
        if (LockedEnum.UN_LOCKED.equals(this.accountNotLocked)) {
            return true;
        }
        return false;
    }

    /**
     * 是否锁定
     */
    public static int transformationAccountNotLocked(boolean flag){
        if (flag) {
            return LockedEnum.UN_LOCKED.getValue();
        }
        return LockedEnum.LOCKED.getValue();
    }
}
