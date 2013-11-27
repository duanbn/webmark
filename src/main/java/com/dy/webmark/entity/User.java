package com.dy.webmark.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.validation.annotation.CheckPOJO;
import com.duanbn.validation.annotation.CheckString;

@Table
@CheckPOJO
public class User {

    @PrimaryKey(autoIncrement = true)
    private int id;

    @Field(isCanNull = false, length = 20, hasDefault = true)
    @CheckString(isNull = true, length = "(0,20]")
    private String nickname;

    @Field(isCanNull = false)
    @CheckString(isNull = false)
    private String password;

    @Field(isCanNull = false)
    @CheckString(isNull = false)
    private String email;

    @DateTime(isCanNull = false, hasDefault = true)
    private Date regTime = new Date();

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
