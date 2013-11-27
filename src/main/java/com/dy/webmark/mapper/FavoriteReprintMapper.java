package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.FavoriteReprint;

public interface FavoriteReprintMapper {

    @Select("select * from favoritereprint where favoId = #{favoId}")
    public List<FavoriteReprint> getFavoriteReprintList(@Param("favoId") int favoId);

    @Select("select * from favoritereprint where favoId = #{favoId} and userId = #{userId}")
    public FavoriteReprint getFavoriteReprint(@Param("favoId") int favoId, @Param("userId") int userId);

    @Insert("insert into favoritereprint(favoId, userId, clipId) values(#{favoId}, #{userId}, #{clipId})")
    public void insertFavoriteReprint(FavoriteReprint fr);

}
