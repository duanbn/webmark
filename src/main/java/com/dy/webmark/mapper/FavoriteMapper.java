package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.Favorite;

public interface FavoriteMapper {

    @Update("update favorite set howManyReprint = howManyReprint + 1 where id=#{favoId}")
    public void incrReprint(@Param("favoId") int favoId);

    @Update("update favorite set howManyLike = howManyLike + 1 where id=#{favoId}")
    public void incrLike(@Param("favoId") int favoId);

    @Update("update favorite set howManyPopularity = howManyPopularity + 1 where id=#{favoId}")
    public void incrPopluar(@Param("favoId") int favoId);

    @Insert("INSERT INTO favorite(userId, title, description, keyword, url, clipId, highLight, isTop, createTime) "
            + "VALUES(#{userId}, #{title}, #{description}, #{keyword}, #{url}, #{clipId}, #{highLight}, #{isTop}, #{createTime})")
    public void insertFavorite(Favorite favorite);

    @Select("SELECT * FROM favorite where id=#{favoId}")
    public Favorite getFavoriteById(@Param("favoId") int favoId);

}
