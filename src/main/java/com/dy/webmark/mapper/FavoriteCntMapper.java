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
    public List<FavoriteCnt> selectByFavoIds(@Param("ids") List<Integer> f_favoids);

    @Insert("insert into favoritecnt values(#{f_favoid}, #{f_howmanypopularity}, #{f_howmanylike}, #{f_howmanyreprint})")
    public void addFavoriteCnt(FavoriteCnt cnt);

    @Select("select * from favoritecnt where f_favoid=#{f_favoid}")
    public FavoriteCnt get(@Param("f_favoid") int f_favoid);

    @Update("update favoritecnt set f_howmanyreprint = f_howmanyreprint + 1 where f_favoid=#{f_favoid}")
    public void incrReprint(@Param("f_favoid") int f_favoid);

    @Update("update favoritecnt set f_howmanylike = f_howmanylike + 1 where f_favoid=#{f_favoid}")
    public void incrLike(@Param("f_favoid") int f_favoid);

    @Update("update favoritecnt set f_howmanypopularity = f_howmanypopularity + 1 where f_favoid=#{f_favoid}")
    public void incrPopluar(@Param("f_favoid") int f_favoid);

}
