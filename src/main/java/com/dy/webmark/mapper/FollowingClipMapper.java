package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.UserFollowingClip;

public interface FollowingClipMapper {

    @Insert("insert into userfollowingclip(userId, followingClipId) values(#{userId}, #{followingClipId})")
    public void insert(UserFollowingClip followingClip);

    @Select("select * from userfollowingclip where userId=#{userId} limit #{start},#{limit}")
    public List<UserFollowingClip> selectFollowingClips(@Param("userId") int userId, @Param("start") int start,
            @Param("limit") int limit);

    @Delete("delete from userfollowingclip where userId=#{userId} and followingClipId=#{clipId}")
    public void delete(@Param("userId") int userId, @Param("clipId") int clipId);

}
