package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.dy.webmark.entity.User;

public interface UserMapper {

    @SelectProvider(type = UserSql.class, method = "selectByIds")
    public List<User> getUserByIds(@Param("ids") int[] ids);

    @Select("select u_email from user where u_email=#{u_email}")
    public String getEmail(@Param("u_email") String u_email);

    @Select("select * from user where u_email=#{u_email}")
    public User getUserByEmail(@Param("u_email") String u_email);

    @Insert("INSERT INTO user(u_password, u_email, regTime) VALUES(#{u_password}, #{u_email}, #{regTime})")
    @Options(useGeneratedKeys = true, keyProperty = "u_id")
    public void insertUser(User user);

    @Select("SELECT * FROM user where u_id=#{u_id}")
    public User getUserById(@Param("u_id") int u_id);

    @Delete("delete form user where u_id=#{u_id} and u_password=#{u_password}")
    public void deleteUserById(@Param("u_id") int u_id, @Param("u_password") String u_password);

}
