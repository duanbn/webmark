package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.Favorite;

public interface FavoriteMapper {

    @Select("select screenshot from favorite where url=#{url} limit 0, 1")
    public String getByUrl(@Param("url") String url);

    @Select("select * from favorite where userId = #{userId} and url = #{url}")
    public Favorite getByURL(@Param("userId") int userId, @Param("url") String url);

    @Insert("INSERT INTO favorite(userId, title, description, keyword, url, clipId, highLight, isTop, createTime, isReprint, screenshot) "
            + "VALUES(#{userId}, #{title}, #{description}, #{keyword}, #{url}, #{clipId}, #{highLight}, #{isTop}, #{createTime}, #{isReprint}, #{screenshot})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertFavorite(Favorite favorite);

    @Select("SELECT * FROM favorite where id=#{favoId}")
    public Favorite getFavoriteById(@Param("favoId") int favoId);

}
