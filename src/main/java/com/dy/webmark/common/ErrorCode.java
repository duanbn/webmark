package com.dy.webmark.common;

public enum ErrorCode {

    BIZ4001("b4001", "保存收藏夹失败"),

    /**
     * 登录相关
     */
    BIZ3001("b3001", "保存用户登录信息失败"),

    /**
     * “用户收藏”错误码
     */
    BIZ2001("b2001", "添加用户收藏失败"), BIZ2002("b2002", "找不到用户收藏信息"),

    /**
     * 用户错误码
     */
    USER_REG_FAIL("b1001", "用户注册失败"), USER_NOT_EXIST("b1002", "找不到用户信息"), USER_EMAIL_EXIST("b1003", "email已经存在"),
    USER_NICKNAME_EXIST("b1004", "用户昵称已经存在"), EMAIL_NOT_EXIST("b1005", "邮箱不存在哦"),
    USER_PASSWORD_ERROR("b1006", "密码不正确哦");

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final ErrorCode getEnum(String code) {
        for (ErrorCode ec : ErrorCode.values()) {
            if (code.equals(ec.getCode())) {
                return ec;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
