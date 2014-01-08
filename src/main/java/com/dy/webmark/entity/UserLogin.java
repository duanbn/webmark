package com.dy.webmark.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
public class UserLogin {

    @PrimaryKey(autoIncrement = false)
    private String ul_email = "";

    @Field
    private String ul_sessionid = "";

    @DateTime
    private Date ul_logintime = new Date();

    @Field
    private boolean ul_isautologin;

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp ul_updatetime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getUl_email() {
        return ul_email;
    }

    public void setUl_email(String ul_email) {
        this.ul_email = ul_email;
    }

    public String getUl_sessionid() {
        return ul_sessionid;
    }

    public void setUl_sessionid(String ul_sessionid) {
        this.ul_sessionid = ul_sessionid;
    }

    public Date getUl_logintime() {
        return ul_logintime;
    }

    public void setUl_logintime(Date ul_logintime) {
        this.ul_logintime = ul_logintime;
    }

    public boolean isUl_isautologin() {
        return ul_isautologin;
    }

    public void setUl_isautologin(boolean ul_isautologin) {
        this.ul_isautologin = ul_isautologin;
    }

    public Timestamp getUl_updatetime() {
        return ul_updatetime;
    }

    public void setUl_updatetime(Timestamp ul_updatetime) {
        this.ul_updatetime = ul_updatetime;
    }

}
