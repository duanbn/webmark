package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.Favorite;

public interface FavoriteMapper {

    @Select("SELECT count(f_id) FROM favorite WHERE f_clipid=#{f_clipid}")
    public long selectCntByClipId(@Param("f_clipid") int f_clipid);

    @Select("SELECT f_screenshot FROM favorite WHERE f_clipid=#{clipId} AND f_screenshot != 'noscreen.jpg'")
    public List<String> selectScreenshot(@Param("clipId") int clipId);

    @Select("SELECT count(f_id) from favorite where f_userid=#{f_userid}")
    public long selectCnt(int f_userid);

    @Select("select * from favorite where f_clipid=#{f_clipid} limit #{start}, #{limit}")
    public List<Favorite> selectByClip(@Param("f_clipid") int f_clipid, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select f_screenshot from favorite where f_url=#{f_url} limit 0, 1")
    public String getByUrl(@Param("f_url") String f_url);

    @Select("select * from favorite where f_userid = #{f_userid} and f_url = #{f_url}")
    public Favorite getByURL(@Param("f_userid") int f_userid, @Param("f_url") String f_url);

    @Insert("INSERT INTO favorite(f_userid, f_title, f_desc, f_keyword, f_url, f_clipid, f_highlight, f_istop, f_createtime, f_isreprint, f_screenshot) "
            + "VALUES(#{f_userid}, #{f_title}, #{f_desc}, #{f_keyword}, #{f_url}, #{f_clipid}, #{f_highlight}, #{f_istop}, #{f_createtime}, #{f_isreprint}, #{f_screenshot})")
    @Options(useGeneratedKeys = true, keyProperty = "f_id")
    public void insertFavorite(Favorite favorite);

    @Select("SELECT * FROM favorite where f_id=#{favoId}")
    public Favorite getFavoriteById(@Param("favoId") int favoId);

}
