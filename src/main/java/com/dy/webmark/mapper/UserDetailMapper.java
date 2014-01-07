package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.UserDetail;


public interface UserDetailMapper {

    @Select("SELECT * FROM userdetail where ud_userid=#{userId}")
    public UserDetail selectByUserId(@Param("userId") int userId);

}
