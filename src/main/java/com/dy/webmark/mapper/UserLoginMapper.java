package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.UserLogin;

public interface UserLoginMapper {

    @Update("update userlogin set ul_sessionid=#{ul_sessionid}, ul_logintime=#{ul_logintime}, ul_isautologin=#{ul_isautologin} where ul_email=#{ul_email}")
    public void update(UserLogin login);

    @Insert("insert into userlogin(ul_email, ul_sessionid, ul_logintime, ul_isautologin) value(#{ul_email}, #{ul_sessionid}, #{ul_logintime}, #{ul_isautologin})")
    public void insert(UserLogin login);

    @Select("select * from userlogin where ul_email=#{ul_email} AND ul_sessionid=#{ul_sessionid}")
    public UserLogin getByEmailSessionId(@Param("ul_email") String ul_email, @Param("ul_sessionid") String ul_sessionid);

    @Select("select * from userlogin where ul_email=#{ul_email}")
    public UserLogin getByEmail(@Param("ul_email") String ul_email);

    @Delete("delete from userlogin where ul_email=#{ul_email}")
    public void delete(@Param("ul_email") String ul_email);

}
