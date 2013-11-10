package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.UserLogin;

public interface UserLoginMapper {

    @Update("update userlogin set sessionId=#{sessionId}, loginTime=#{loginTime}, isAutoLogin=#{isAutoLogin} where email=#{email}")
    public void update(UserLogin login);

    @Insert("insert into userlogin(email, sessionId, loginTime, isAutoLogin) value(#{email}, #{sessionId}, #{loginTime}, #{isAutoLogin})")
    public void insert(UserLogin login);

    @Select("select * from userlogin where email=#{email} AND sessionId=#{sessionId}")
    public UserLogin getByEmailSessionId(@Param("email") String email, @Param("sessionId") String sessionId);

    @Select("select * from userlogin where email=#{email}")
    public UserLogin getByEmail(@Param("email") String email);

    @Delete("delete from userlogin where email=#{email}")
    public void delete(@Param("email") String email);

}
