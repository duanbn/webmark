package com.dy.webmark.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.Index;
import com.duanbn.mydao.annotation.Indexes;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.validation.annotation.CheckNumber;
import com.duanbn.validation.annotation.CheckPOJO;
import com.duanbn.validation.annotation.CheckString;

@Table
@Indexes({ @Index(field = "fc_userid") })
@CheckPOJO
public class FavoriteClip {

    @PrimaryKey(autoIncrement = true)
    private int fc_id; // 主键

    @Field(isCanNull = false, hasDefault = true, length = 45)
    @CheckString(isNull = false, message = "优夹名称不能为空")
    private String fc_name; // 优夹名称

    @Field(isCanNull = false)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int fc_userid; // 所属用户id

    @Field
    private boolean fc_isdefault; // 是否是默认优夹

    @Field(hasDefault = true)
    private int fc_favocnt; // 包含的收录数

    @Field(isCanNull = true, hasDefault = true)
    private String fc_desc; // 优夹描述

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getFc_id() {
        return fc_id;
    }

    public void setFc_id(int fc_id) {
        this.fc_id = fc_id;
    }

    public String getFc_name() {
        return fc_name;
    }

    public void setFc_name(String fc_name) {
        this.fc_name = fc_name;
    }

    public int getFc_userid() {
        return fc_userid;
    }

    public void setFc_userid(int fc_userid) {
        this.fc_userid = fc_userid;
    }

    public boolean isFc_isdefault() {
        return fc_isdefault;
    }

    public void setFc_isdefault(boolean fc_isdefault) {
        this.fc_isdefault = fc_isdefault;
    }

    public int getFc_favocnt() {
        return fc_favocnt;
    }

    public void setFc_favocnt(int fc_favocnt) {
        this.fc_favocnt = fc_favocnt;
    }

    public String getFc_desc() {
        return fc_desc;
    }

    public void setFc_desc(String fc_desc) {
        this.fc_desc = fc_desc;
    }
}
