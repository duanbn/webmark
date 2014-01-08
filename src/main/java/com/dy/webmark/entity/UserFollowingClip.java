package com.dy.webmark.entity;

import java.util.Date;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
@Indexes({ @Index(field = "ufc_userid") })
public class UserFollowingClip {

    @PrimaryKey(autoIncrement = true)
    private int ufc_id;

    @Field
    private int ufc_userid;

    @Field
    private int ufc_followingclipid;

    @DateTime
    private Date ufc_followtime = new Date();

    public int getUfc_id() {
        return ufc_id;
    }

    public void setUfc_id(int ufc_id) {
        this.ufc_id = ufc_id;
    }

    public int getUfc_userid() {
        return ufc_userid;
    }

    public void setUfc_userid(int ufc_userid) {
        this.ufc_userid = ufc_userid;
    }

    public int getUfc_followingclipid() {
        return ufc_followingclipid;
    }

    public void setUfc_followingclipid(int ufc_followingclipid) {
        this.ufc_followingclipid = ufc_followingclipid;
    }

    public Date getUfc_followtime() {
        return ufc_followtime;
    }

    public void setUfc_followtime(Date ufc_followtime) {
        this.ufc_followtime = ufc_followtime;
    }

}
