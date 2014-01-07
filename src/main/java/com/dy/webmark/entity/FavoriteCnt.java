package com.dy.webmark.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
public class FavoriteCnt {

    @PrimaryKey(autoIncrement = false)
    private int f_favoid;

    @Field(isCanNull = false, hasDefault = true)
    private int f_howmanypopularity; // 人气

    @Field(isCanNull = false, hasDefault = true)
    private int f_howmanylike; // 喜欢

    @Field(isCanNull = false, hasDefault = true)
    private int f_howmanyreprint; // 转录

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getF_favoid() {
        return f_favoid;
    }

    public void setF_favoid(int f_favoid) {
        this.f_favoid = f_favoid;
    }

    public int getF_howmanypopularity() {
        return f_howmanypopularity;
    }

    public void setF_howmanypopularity(int f_howmanypopularity) {
        this.f_howmanypopularity = f_howmanypopularity;
    }

    public int getF_howmanylike() {
        return f_howmanylike;
    }

    public void setF_howmanylike(int f_howmanylike) {
        this.f_howmanylike = f_howmanylike;
    }

    public int getF_howmanyreprint() {
        return f_howmanyreprint;
    }

    public void setF_howmanyreprint(int f_howmanyreprint) {
        this.f_howmanyreprint = f_howmanyreprint;
    }

}
