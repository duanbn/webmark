package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.User;

public interface UserMapper {

    @SelectProvider(type = UserSql.class, method = "selectByIds")
    public List<User> getUserByIds(@Param("ids") int[] ids);

    @Select("select email from user where email=#{email}")
    public String getEmail(@Param("email") String email);

    @Select("select * from user where nickname=#{nickname}")
    public User getUserByNickName(@Param("nickname") String nickname);

    @Select("select * from user where email=#{email}")
    public User getUserByEmail(@Param("email") String email);

    @Insert("INSERT INTO user(password, email, regTime) VALUES(#{password}, #{email}, #{regTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertUser(User user);

    @Select("SELECT * FROM user where id=#{id}")
    public User getUserById(@Param("id") int id);

    @Update("update user set nickname=#{nickname} where id=#{id}")
    public void updateNickName(@Param("id") int id, @Param("nickname") String name);

    @Delete("delete form user where id=#{id} and password=#{password}")
    public void deleteUserById(@Param("id") int id, @Param("password") String password);

}
