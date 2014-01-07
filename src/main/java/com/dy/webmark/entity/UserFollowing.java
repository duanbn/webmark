package com.dy.webmark.entity;

import java.util.Date;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
@Indexes({ @Index(field = "uf_userid, uf_isread"), @Index(field = "uf_userid, uf_followingid"), @Index(field = "uf_followingid") })
public class UserFollowing {

    @PrimaryKey(autoIncrement = true)
    private int uf_id;

    @Field(isCanNull = false, hasDefault = true)
    private int uf_userid;

    @Field(isCanNull = false, hasDefault = true)
    private int uf_followingid;

    @Field(isCanNull = false, hasDefault = true)
    private boolean uf_isread;

    @DateTime(isCanNull = false, hasDefault = true)
    private Date uf_followtime = new Date();

    public int getUf_id() {
        return uf_id;
    }

    public void setUf_id(int uf_id) {
        this.uf_id = uf_id;
    }

    public int getUf_userid() {
        return uf_userid;
    }

    public void setUf_userid(int uf_userid) {
        this.uf_userid = uf_userid;
    }

    public int getUf_followingid() {
        return uf_followingid;
    }

    public void setUf_followingid(int uf_followingid) {
        this.uf_followingid = uf_followingid;
    }

    public boolean isUf_isread() {
        return uf_isread;
    }

    public void setUf_isread(boolean uf_isread) {
        this.uf_isread = uf_isread;
    }

    public Date getUf_followtime() {
        return uf_followtime;
    }

    public void setUf_followtime(Date uf_followtime) {
        this.uf_followtime = uf_followtime;
    }

}
