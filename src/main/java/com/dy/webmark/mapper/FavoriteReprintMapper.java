package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.FavoriteReprint;

public interface FavoriteReprintMapper {

    @Select("select * from favoritereprint where fromFavoId = #{fromFavoId}")
    public List<FavoriteReprint> getFavoriteReprintList(@Param("fromFavoId") int fromFavoId);

    @Select("select * from favoritereprint where fromFavoId = #{fromFavoId} and userId = #{userId}")
    public FavoriteReprint getFavoriteReprint(@Param("fromFavoId") int fromFavoId, @Param("userId") int userId);

    @Insert("insert into favoritereprint(id, fromFavoId, userId, clipId) values(#{id}, #{fromFavoId}, #{userId}, #{clipId})")
    public void insertFavoriteReprint(FavoriteReprint fr);

}
