package com.dy.webmark.exception;

public enum ErrorCode {

    /**
     * 用户错误码
     */
    USER_REG_FAIL("c1001", "用户注册失败"), USER_NOT_EXIST("c1002", "找不到用户信息"), USER_EMAIL_EXIST("c1003", "email已经存在"),
    USER_NICKNAME_EXIST("c1004", "用户昵称已经存在"), EMAIL_NOT_EXIST("c1005", "邮箱不存在哦"), USER_PASSWORD_ERROR("c1006", "密码不正确哦"),

    /**
     * “用户收藏”错误码
     */
    FAVORITE_ADD_FAIL("c2001", "添加用户收藏失败"), FAVORITE_NOT_EXIST("c2002", "找不到用户收藏信息");

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
