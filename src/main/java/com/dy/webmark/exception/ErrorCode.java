package com.dy.webmark.exception;

public enum ErrorCode {

    /**
     * 用户错误码
     */
    USER_REG_FAIL(1001, "用户注册失败"), USER_NOT_EXIST(1002, "找不到用户信息"), USER_EMAIL_EXIST(1003, "email已经存在"),
    USER_NICKNAME_EXIST(1004, "用户昵称已经存在"), EMAIL_NOT_EXIST(1005, "email不存在"), USER_PASSWORD_ERROR(1006, "密码错误"),

    /**
     * “用户收藏”错误码
     */
    FAVORITE_ADD_FAIL(2001, "添加用户收藏失败"), FAVORITE_NOT_EXIST(2002, "找不到用户收藏信息");

    private int code;

    private String value;

    ErrorCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static final ErrorCode getEnum(int code) {
        for (ErrorCode sc : ErrorCode.values()) {
            if (code == sc.getCode()) {
                return sc;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
