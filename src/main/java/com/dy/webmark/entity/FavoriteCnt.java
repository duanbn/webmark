package com.dy.webmark.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;

@Table
public class FavoriteCnt {

    @PrimaryKey(autoIncrement = false)
    private int favoId;

    @Field(isCanNull = false, hasDefault = true)
    private int howManyPopularity; // 人气

    @Field(isCanNull = false, hasDefault = true)
    private int howManyLike; // 喜欢

    @Field(isCanNull = false, hasDefault = true)
    private int howManyReprint; // 转录

    public int getFavoId() {
        return favoId;
    }

    public void setFavoId(int favoId) {
        this.favoId = favoId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
