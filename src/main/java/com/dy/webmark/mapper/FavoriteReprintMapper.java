package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.FavoriteReprint;

public interface FavoriteReprintMapper {

    @Select("select * from favoritereprint where fr_fromfavoid = #{fr_fromfavoid}")
    public List<FavoriteReprint> getFavoriteReprintList(@Param("fr_fromfavoid") int fr_fromfavoid);

    @Select("select * from favoritereprint where fr_fromfavoid = #{fr_fromfavoid} and fr_userid = #{fr_userid}")
    public FavoriteReprint getFavoriteReprint(@Param("fr_fromfavoid") int fr_fromfavoid, @Param("fr_userid") int fr_userid);

    @Insert("insert into favoritereprint(id, fr_fromfavoid, fr_userid, fr_clipid) values(#{id}, #{fr_fromfavoid}, #{fr_userid}, #{fr_clipid})")
    public void insertFavoriteReprint(FavoriteReprint fr);

}
