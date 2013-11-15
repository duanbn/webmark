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

@Table
@Indexes({ @Index(field = "userId,clipId"), @Index(field = "clipId") })
@CheckPOJO
public class Favorite {

    @PrimaryKey(autoIncrement = true)
    private int id;

    @Field(isCanNull = false, hasDefault = true)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int userId;

    @Field(hasDefault = true)
    private String title;

    @Field(hasDefault = true)
    private String description;

    @Field(hasDefault = true)
    private String keyword;

    @Field(isCanNull = false, hasDefault = true)
    @CheckURL(isNull = false, message = "请填写有效的url地址")
    private String url;

    @Field(isCanNull = false)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int clipId; // 优夹id

    @Field(hasDefault = true)
    private boolean highLight;

    @Field(hasDefault = true)
    private boolean isTop;

    @DateTime
    private Date createTime = new Date();

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp updateTime;

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
