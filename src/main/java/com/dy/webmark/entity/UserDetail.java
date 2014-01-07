package com.dy.webmark.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.duanbn.mydao.annotation.Field;
import com.duanbn.mydao.annotation.PrimaryKey;
import com.duanbn.mydao.annotation.Table;
import com.duanbn.validation.annotation.CheckPOJO;
import com.duanbn.validation.annotation.CheckString;

@Table
@CheckPOJO
public class UserDetail {

    @PrimaryKey(autoIncrement = false)
    private int ud_userid;

    @Field(isCanNull = true, length = 20, hasDefault = true)
    @CheckString(isNull = true, length = "(0,20]")
    private String ud_nickname; // 昵称

    @Field(isCanNull = true, length = 1, hasDefault = true)
    private String ud_sex; // 性别

    @Field(isCanNull = true, hasDefault = true)
    private String ud_comefrom; // 来自

    @Field(isCanNull = true, hasDefault = true)
    private String ud_job; // 职业

    @Field(isCanNull = true, length = 45, hasDefault = true)
    private String ud_sign; // 个人签名

    @Field(isCanNull = true, hasDefault = true)
    private String ud_avatar; // 用户头像

    public UserDetail() {

    }

    public UserDetail(int userId) {
        this.ud_userid = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getUd_userid() {
        return ud_userid;
    }

    public void setUd_userid(int ud_userid) {
        this.ud_userid = ud_userid;
    }

    public String getUd_nickname() {
        return ud_nickname;
    }

    public void setUd_nickname(String ud_nickname) {
        this.ud_nickname = ud_nickname;
    }

    public String getUd_sex() {
        return ud_sex;
    }

    public void setUd_sex(String ud_sex) {
        this.ud_sex = ud_sex;
    }

    public String getUd_comefrom() {
        return ud_comefrom;
    }

    public void setUd_comefrom(String ud_comefrom) {
        this.ud_comefrom = ud_comefrom;
    }

    public String getUd_job() {
        return ud_job;
    }

    public void setUd_job(String ud_job) {
        this.ud_job = ud_job;
    }

    public String getUd_sign() {
        return ud_sign;
    }

    public void setUd_sign(String ud_sign) {
        this.ud_sign = ud_sign;
    }

    public String getUd_avatar() {
        return ud_avatar;
    }

    public void setUd_avatar(String ud_avatar) {
        this.ud_avatar = ud_avatar;
    }
}
