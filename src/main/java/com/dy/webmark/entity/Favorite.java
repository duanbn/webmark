package com.dy.webmark.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.validation.annotation.CheckNumber;
import com.duanbn.validation.annotation.CheckPOJO;
import com.duanbn.validation.annotation.CheckURL;

/**
 * 收录表
 * 
 * @author duanbn
 * 
 */
@Table
@Indexes({ @Index(field = "userId,clipId"), @Index(field = "clipId") })
@CheckPOJO
public class Favorite {

    @PrimaryKey(autoIncrement = true)
    private int id; // 主键

    @Field(isCanNull = false, hasDefault = true)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int userId; // 收录人id

    @Field(hasDefault = true)
    private String title; // 收录标题

    @Field(hasDefault = true)
    private String description; // 收录描述

    @Field(hasDefault = true)
    private String keyword; // 收录的关键字，从被收录的网页获取

    @Field(isCanNull = false, hasDefault = true)
    @CheckURL(isNull = false, message = "请填写有效的url地址")
    private String url; // 收录的网页地址

    @Field(isCanNull = false)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int clipId; // 优夹id

    @Field(hasDefault = true)
    private boolean highLight; // 高亮

    @Field(hasDefault = true)
    private boolean isTop; // 置顶

    @Field(isCanNull = false, hasDefault = true)
    private int howManyPopularity; // 人气

    @Field(isCanNull = false, hasDefault = true)
    private int howManyLike; // 喜欢

    @Field(isCanNull = false, hasDefault = true)
    private int howManyReprint; // 转录

    @DateTime
    private Date createTime = new Date(); // 收录时间

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp updateTime; // 修改时间戳

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getClipId() {
        return clipId;
    }

    public void setClipId(int clipId) {
        this.clipId = clipId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHighLight() {
        return highLight;
    }

    public void setHighLight(boolean highLight) {
        this.highLight = highLight;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public int getHowManyPopularity() {
        return howManyPopularity;
    }

    public void setHowManyPopularity(int howManyPopularity) {
        this.howManyPopularity = howManyPopularity;
    }

    public int getHowManyLike() {
        return howManyLike;
    }

    public void setHowManyLike(int howManyLike) {
        this.howManyLike = howManyLike;
    }

    public int getHowManyReprint() {
        return howManyReprint;
    }

    public void setHowManyReprint(int howManyReprint) {
        this.howManyReprint = howManyReprint;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
