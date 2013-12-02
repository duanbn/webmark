package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.FavoriteCnt;

public interface FavoriteCntMapper {

    @SelectProvider(type = FavoriteCntSql.class, method = "selectByIds")
    public List<FavoriteCnt> selectByFavoIds(@Param("ids") List<Integer> favoIds);

    @Insert("insert into favoritecnt values(#{favoId}, #{howManyPopularity}, #{howManyLike}, #{howManyReprint})")
    public void addFavoriteCnt(FavoriteCnt cnt);

    @Select("select * from favoritecnt where favoId=#{favoId}")
    public FavoriteCnt get(@Param("favoId") int favoId);

    @Update("update favoritecnt set howManyReprint = howManyReprint + 1 where favoId=#{favoId}")
    public void incrReprint(@Param("favoId") int favoId);

    @Update("update favoritecnt set howManyLike = howManyLike + 1 where favoId=#{favoId}")
    public void incrLike(@Param("favoId") int favoId);

    @Update("update favoritecnt set howManyPopularity = howManyPopularity + 1 where favoId=#{favoId}")
    public void incrPopluar(@Param("favoId") int favoId);

}
