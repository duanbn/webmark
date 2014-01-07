package com.dy.webmark.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.DateTime;
import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.validation.annotation.CheckNumber;
import com.duanbn.validation.annotation.CheckPOJO;
import com.duanbn.validation.annotation.CheckString;
import com.duanbn.validation.annotation.CheckURL;

/**
 * 收录表
 * 
 * @author duanbn
 * 
 */
@Table
@Indexes({ @Index(field = "userId,clipId"), @Index(field = "url"), @Index(field = "userId,url", isUnique = true) })
@CheckPOJO
public class Favorite {

    @PrimaryKey(autoIncrement = true)
    private int f_id; // 主键

    @Field(isCanNull = false, hasDefault = true)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int f_userid; // 收录人id

    @Field(hasDefault = true)
    private String f_title; // 收录标题

    @Field(hasDefault = true)
    private String f_desc; // 收录描述

    @Field(hasDefault = true)
    private String f_keyword; // 收录的关键字，从被收录的网页获取

    @Field(isCanNull = false, hasDefault = true)
    @CheckURL(isNull = false, message = "请填写有效的url地址")
    private String f_url; // 收录的网页地址

    @Field(isCanNull = false)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int f_clipid; // 优夹id

    @Field(hasDefault = true)
    private boolean f_highlight; // 高亮

    @Field(hasDefault = true)
    private boolean f_istop; // 置顶

    @Field(hasDefault = true)
    private boolean f_isreprint; // 是否是转录的

    @DateTime
    private Date f_createtime = new Date(); // 收录时间

    @com.duanbn.mydao.annotation.Timestamp
    private Timestamp f_updatetime; // 修改时间戳

    @Field(isCanNull = false, hasDefault = true)
    @CheckString(isNull = false)
    private String f_screenshot;

    private List<User> reprintUserList;

    private FavoriteCnt cnt;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getF_userid() {
        return f_userid;
    }

    public void setF_userid(int f_userid) {
        this.f_userid = f_userid;
    }

    public String getF_title() {
        return f_title;
    }

    public void setF_title(String f_title) {
        this.f_title = f_title;
    }

    public String getF_desc() {
        return f_desc;
    }

    public void setF_desc(String f_desc) {
        this.f_desc = f_desc;
    }

    public String getF_keyword() {
        return f_keyword;
    }

    public void setF_keyword(String f_keyword) {
        this.f_keyword = f_keyword;
    }

    public String getF_url() {
        return f_url;
    }

    public void setF_url(String f_url) {
        this.f_url = f_url;
    }

    public int getF_clipid() {
        return f_clipid;
    }

    public void setF_clipid(int f_clipid) {
        this.f_clipid = f_clipid;
    }

    public boolean isF_highlight() {
        return f_highlight;
    }

    public void setF_highlight(boolean f_highlight) {
        this.f_highlight = f_highlight;
    }

    public boolean isF_istop() {
        return f_istop;
    }

    public void setF_istop(boolean f_istop) {
        this.f_istop = f_istop;
    }

    public boolean isF_isreprint() {
        return f_isreprint;
    }

    public void setF_isreprint(boolean f_isreprint) {
        this.f_isreprint = f_isreprint;
    }

    public Date getF_createtime() {
        return f_createtime;
    }

    public void setF_createtime(Date f_createtime) {
        this.f_createtime = f_createtime;
    }

    public Timestamp getF_updatetime() {
        return f_updatetime;
    }

    public void setF_updatetime(Timestamp f_updatetime) {
        this.f_updatetime = f_updatetime;
    }

    public String getF_screenshot() {
        return f_screenshot;
    }

    public void setF_screenshot(String f_screenshot) {
        this.f_screenshot = f_screenshot;
    }

    public List<User> getReprintUserList() {
        return reprintUserList;
    }

    public void setReprintUserList(List<User> reprintUserList) {
        this.reprintUserList = reprintUserList;
    }

    public FavoriteCnt getCnt() {
        return cnt;
    }

    public void setCnt(FavoriteCnt cnt) {
        this.cnt = cnt;
    }

}
