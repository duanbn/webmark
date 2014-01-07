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
    private int u_id; // 主键

    @Field(isCanNull = false)
    @CheckString(isNull = false)
    private String u_password; // 密码

    @Field(isCanNull = false)
    @CheckString(isNull = false)
    private String u_email; // 邮箱

    @DateTime(isCanNull = false, hasDefault = true)
    private Date u_regtime = new Date(); // 注册时间

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp u_updatetime; // 最后更新时间

    private UserDetail detail; // 用户详细信息

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    public int getU_id() {
        return u_id;
    }
    
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    
    public String getU_password() {
        return u_password;
    }
    
    public void setU_password(String u_password) {
        this.u_password = u_password;
    }
    
    public String getU_email() {
        return u_email;
    }
    
    public void setU_email(String u_email) {
        this.u_email = u_email;
    }
    
    public Date getU_regtime() {
        return u_regtime;
    }
    
    public void setU_regtime(Date u_regtime) {
        this.u_regtime = u_regtime;
    }
    
    public Timestamp getU_updatetime() {
        return u_updatetime;
    }
    
    public void setU_updatetime(Timestamp u_updatetime) {
        this.u_updatetime = u_updatetime;
    }
    
    public UserDetail getDetail() {
        return detail;
    }
    
    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }
}
