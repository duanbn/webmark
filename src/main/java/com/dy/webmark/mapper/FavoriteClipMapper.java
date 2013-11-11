package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.FavoriteClip;

public interface FavoriteClipMapper {

    @Insert("insert into favoriteclip(name, userId) values(#{name}, #{userId})")
    public void insert(FavoriteClip clip);

    @Select("select * from favoriteclip where id=#{id}")
    public FavoriteClip selectById(@Param("id") int id);

    @Select("select * from favoriteclip where userId=#{userId}")
    public List<FavoriteClip> selectByUserId(@Param("userId") int userId);

    @Update("update favoriteclip set name=#{name} where id=#{id}")
    public void update(FavoriteClip clip);

    @Delete("delete from favoriteclip where id=#{id}")
    public void deleteById(@Param("id") int id);

}
