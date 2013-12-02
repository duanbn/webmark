package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.FavoriteClip;

public interface FavoriteClipMapper {

    @Update("update favoriteclip set favoCnt = favoCnt + 1 where id=#{favoClipId}")
    public void incrFavoCnt(@Param("favoClipId") int favoClipId);

    @Select("select * from favoriteclip where userId=#{userId} and name like '${name}%'")
    public List<FavoriteClip> selectByNamePrefix(@Param("userId") int userId, @Param("name") String name);

    @Insert("insert into favoriteclip(name, userId, isDefault) values(#{name}, #{userId}, #{isDefault})")
    public void insert(FavoriteClip clip);

    @Select("select * from favoriteclip where id=#{id}")
    public FavoriteClip selectById(@Param("id") int id);

    @Select("select * from favoriteclip where userId=#{userId} limit #{start},#{limit}")
    public List<FavoriteClip> selectByUserId(@Param("userId") int userId, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select * from favoriteclip where userId=#{userId} and name=#{name}")
    public FavoriteClip selectByName(@Param("userId") int userId, @Param("name") String name);

    @Update("update favoriteclip set name=#{name} where id=#{id}")
    public void update(FavoriteClip clip);

    @Delete("delete from favoriteclip where id=#{id}")
    public void deleteById(@Param("id") int id);

}
