package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.Favorite;

public interface FavoriteMapper {

    @Insert("INSERT INTO favorite(userId, title, description, keyword, url) VALUES(#{userId}, #{title}, #{description}, #{keyword}, #{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insertFavorite(Favorite favorite);

    @Select("SELECT * FROM favorite where id=#{favoId}")
    public Favorite getFavoriteById(int favoId);

}
