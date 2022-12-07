package com.emas.common.constant;

/**
 * @Author: apple
 * @Description: 返回码定义
 * 规定:
 * 4001-5001 自定义返回值
 */
public enum ResultCustomCode {
    /* 参数错误：4001-5001 */
    PARAM_MENU_NOT_VALID(4001, "存在子菜单,不能删除"),
    DATA_ALREADY_EXISTS(5001, "数据已存在");
    private Integer code;
    private String message;

    ResultCustomCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCustomCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
