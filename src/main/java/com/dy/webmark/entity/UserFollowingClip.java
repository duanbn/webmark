package com.dy.webmark.entity;

import java.util.Date;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
@Indexes({ @Index(field = "userId") })
public class UserFollowingClip {

    @PrimaryKey(autoIncrement = true)
    private int id;

    @Field(isCanNull = false, hasDefault = true)
    private int userId;

    @Field(isCanNull = false, hasDefault = true)
    private int followingClipId;

    @DateTime(hasDefault = true, isCanNull = false)
    private Date followTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingClipId() {
        return followingClipId;
    }

    public void setFollowingClipId(int followingClipId) {
        this.followingClipId = followingClipId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

}
