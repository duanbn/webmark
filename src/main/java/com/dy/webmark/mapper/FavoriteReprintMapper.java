package com.dy.webmark.mapper;

import org.apache.ibatis.annotations.Insert;

import com.dy.webmark.entity.FavoriteReprint;

public interface FavoriteReprintMapper {

    @Insert("insert into favoritereprint(fromFavoId, userId, clipId) values(#fromFavoId, #userId, #clipId)")
    public void insertFavoriteReprint(FavoriteReprint fr);

}
