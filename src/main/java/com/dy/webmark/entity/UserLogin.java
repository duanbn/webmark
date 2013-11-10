package com.dy.webmark.entity;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.mydao.annotation.Timestamp;

@Table
public class UserLogin {

    @PrimaryKey(autoIncrement = false)
    private String email;

    @Field(isCanNull = false, hasDefault = false)
    private String sessionId;

    @Timestamp(isCanNull = false)
    private java.sql.Timestamp loginTime = new java.sql.Timestamp(System.currentTimeMillis());

    @Field(hasDefault = true)
    private boolean isAutoLogin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public java.sql.Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(java.sql.Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean isAutoLogin) {
        this.isAutoLogin = isAutoLogin;
    }

}
