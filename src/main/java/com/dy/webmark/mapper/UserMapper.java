package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.User;

public interface UserMapper {

    @Select("select email from user where email=#{email}")
    public String getEmail(@Param("email") String email);

    @Select("select * from user where nickname=#{nickname}")
    public User getUserByNickName(@Param("nickname") String nickname);

    @Select("select * from user where email=#{email}")
    public User getUserByEmail(@Param("email") String email);

    @Insert("INSERT INTO user(password, email) VALUES(#{password}, #{email})")
    public int insertUser(User user);

    @Select("SELECT * FROM user where id=#{id}")
    public User getUserById(@Param("id") int id);

    @Update("update user set nickname=#{nickname} where id=#{id}")
    public void updateNickName(@Param("id") int id, @Param("nickname") String name);

    @Delete("delete form user where id=#{id} and password=#{password}")
    public void deleteUserById(@Param("id") int id, @Param("password") String password);

}
