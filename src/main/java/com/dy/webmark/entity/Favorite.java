package com.dy.webmark.entity;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
public class Favorite {

    @PrimaryKey(autoIncrement = true)
    private int id;

    @Field(isCanNull = false, hasDefault = true)
    private int userId;

    @Field(isCanNull = false, hasDefault = true)
    private String title;

    @Field(isCanNull = false, hasDefault = true)
    private String description;

    @Field(isCanNull = false, hasDefault = true)
    private String keyword;

    @Field(isCanNull = false, hasDefault = true)
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
