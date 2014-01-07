package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dy.webmark.entity.UserFollowingClip;

public interface UserFollowingClipMapper {

    @Insert("insert into userfollowingclip(ufc_userid, ufc_followingclipid) values(#{ufc_userid}, #{ufc_followingclipid})")
    public void insert(UserFollowingClip followingClip);

    @Select("select * from userfollowingclip where ufc_userid=#{ufc_userid} limit #{start},#{limit}")
    public List<UserFollowingClip> selectFollowingClips(@Param("ufc_userid") int ufc_userid, @Param("start") int start,
            @Param("limit") int limit);

    @Delete("delete from userfollowingclip where ufc_userid=#{ufc_userid} and ufc_followingclipid=#{clipId}")
    public void delete(@Param("ufc_userid") int ufc_userid, @Param("clipId") int clipId);

}
