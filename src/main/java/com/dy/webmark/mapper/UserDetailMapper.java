package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.UserDetail;


public interface UserDetailMapper {

    @Insert("INSERT INTO userdetail(ud_userid, ud_nickname, ud_sex, ud_comefrom, ud_job, ud_sign, ud_avatar) values(#{ud_userid}, #{ud_nickname}, #{ud_sex}, #{ud_comefrom}, #{ud_job}, #{ud_sign}, #{ud_avatar})")
    public void insertUserDetail(UserDetail detail);

    @Select("SELECT * FROM userdetail where ud_userid=#{userId}")
    public UserDetail selectByUserId(@Param("userId") int userId);

}
