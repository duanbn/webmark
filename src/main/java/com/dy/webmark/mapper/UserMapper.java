package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.User;

public interface UserMapper {

    @Insert("INSERT INTO user(name, password, email) VALUES(#{name}, #{password}, #{email})")
    public int insertUser(User user);

    @Select("SELECT * FROM user where id=#{id}")
    public User getUserById(@Param("id") int id);

    @Select("SELECT name FROM user where name=#{name}")
    public User getUserByName(@Param("name") String name);

    @Select("SELECT * FROM user where name=#{name} AND password=#{password}")
    public User getUserByNameWithPwd(@Param("name") String name, @Param("password") String password);

    @Select("SELECT email FROM user where email=#{email}")
    public User getUserByEmail(@Param("email") String email);

    @Select("SELECT * FROM user where email=#{email} AND password=#{password}")
    public User getUserByEmailWithPwd(@Param("email") String email, @Param("password") String password);

    @Delete("DELETE FROM user where name=#{name} AND password=#{password}")
    public void deleteUserByName(@Param("name") String name, @Param("password") String password);

}
