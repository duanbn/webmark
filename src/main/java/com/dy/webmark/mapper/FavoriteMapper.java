package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.Favorite;

public interface FavoriteMapper {

    @Insert("INSERT INTO favorite(userId, title, description, keyword, url, clipId, highLight, isTop, createTime, isReprint) "
            + "VALUES(#{userId}, #{title}, #{description}, #{keyword}, #{url}, #{clipId}, #{highLight}, #{isTop}, #{createTime}, #{isReprint})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertFavorite(Favorite favorite);

    @Select("SELECT * FROM favorite where id=#{favoId}")
    public Favorite getFavoriteById(@Param("favoId") int favoId);

}
