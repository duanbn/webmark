package com.dy.webmark.common;

public enum ErrorCode {

    /**
     * 转录相关错误
     */
    BIZ6001("B6001", "保存转录信息失败"), BIZ6002("B6002", "已经转录过此收录"),

    /**
     * 通用错误码
     */
    BIZ5001("B5001", "输入参数错误"), BIZ5002("B5002", "未知错误"), BIZ5003("B5003", "数据库错误"),

    /**
     * 优夹相关错误码
     */
    BIZ4001("B4001", "保存收藏夹失败"), BIZ4002("B4002", "找不到优夹"), BIZ4003("B4003", "优夹已经存在"),

    /**
     * 登录相关错误码
     */
    BIZ3001("B3001", "保存用户登录信息失败"),

    /**
     * 用户收录相关错误码
     */
    BIZ2001("B2001", "添加用户收藏失败"), BIZ2002("B2002", "找不到用户收藏信息"), BIZ2003("B2003", "新增喜欢数失败"), BIZ2004("B2004",
            "新增人气数失败"), BIZ2005("B2005", "新增转录数失败"), BIZ2006("B2006", "收录已经存在"), BIZ2007("B2007", "生成缩略图失败"),

    /**
     * 用户错误码
     */
    BIZ1001("B1001", "用户注册失败"), BIZ1002("B1002", "找不到用户信息"), BIZ1003("B1003", "email已经存在"),
    BIZ1004("B1004", "用户昵称已经存在"), BIZ1005("B1005", "邮箱不存在哦"), BIZ1006("B1006", "密码不正确哦");

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
