package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.FavoriteClip;

public interface FavoriteClipMapper {

    @Select("SELECT count(fc_id) from favoriteclip where fc_userid=#{fc_userid}")
    public long selectCnt(@Param("fc_userid") int fc_userid);

    @SelectProvider(type = FavoriteClipSql.class, method = "selectByIds")
    public List<FavoriteClip> selectByIds(@Param("ids") int[] ids);

    @Update("UPDATE favoriteclip set fc_favocnt = fc_favocnt + 1 WHERE fc_id=#{favoClipId}")
    public void incrFavoCnt(@Param("favoClipId") int favoClipId);

    @Select("SELECT * FROM favoriteclip WHERE fc_userid=#{fc_userid} and fc_name like '${fc_name}%'")
    public List<FavoriteClip> selectByNamePrefix(@Param("fc_userid") int fc_userid, @Param("fc_name") String fc_name);

    @Insert("INSERT INTO favoriteclip(fc_name, fc_userid, fc_isdefault, fc_desc) values(#{fc_name}, #{fc_userid}, #{fc_isdefault}, #{fc_desc})")
    public void insert(FavoriteClip clip);

    @Select("SELECT * FROM favoriteclip WHERE fc_id=#{fc_id}")
    public FavoriteClip selectById(@Param("fc_id") int fc_id);

    @Select("SELECT * FROM favoriteclip WHERE fc_userid=#{fc_userid} limit #{start},#{limit}")
    public List<FavoriteClip> selectByUserId(@Param("fc_userid") int fc_userid, @Param("start") int start,
            @Param("limit") int limit);

    @Select("SELECT * FROM favoriteclip WHERE fc_userid=#{fc_userid} and fc_name=#{fc_name}")
    public FavoriteClip selectByName(@Param("fc_userid") int fc_userid, @Param("fc_name") String fc_name);

    @Update("UPDATE favoriteclip set fc_name=#{fc_name} WHERE fc_id=#{fc_id}")
    public void update(FavoriteClip clip);

    @Delete("DELETE FROM favoriteclip WHERE fc_id=#{fc_id}")
    public void deleteById(@Param("fc_id") int fc_id);

}
