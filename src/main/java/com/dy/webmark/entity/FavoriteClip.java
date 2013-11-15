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
@Indexes({ @Index(field = "userId") })
@CheckPOJO
public class FavoriteClip {

    @PrimaryKey(autoIncrement = true)
    private int id;

    @Field(isCanNull = false, hasDefault = true, length = 45)
    @CheckString(isNull = false, message = "优夹名称不能为空")
    private String name;

    @Field(isCanNull = false)
    @CheckNumber(isNull = false, range = "(0,*]")
    private int userId;

    @Field
    private boolean isDefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
