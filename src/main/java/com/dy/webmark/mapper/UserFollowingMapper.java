package com.dy.webmark.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.dy.webmark.entity.UserFollowing;

public interface UserFollowingMapper {

    @Select("select * from userfollowing where userId=#{userId} and followingId=#{followingId}")
    public UserFollowing selectOne(@Param("userId") int userId, @Param("followingId") int followingId);

    @Insert("insert into userfollowing(userId, followingId, followTime) values(#{userId}, #{followingId}, #{followTime})")
    public void insert(UserFollowing userFollowing);

    @Delete("delete from userfollowing where userId=#{userId} and followingId=#{followingId}")
    public void delete(@Param("userId") int userId, @Param("followingId") int followingId);

    @Select("select * from userfollowing where userId=#{userId} limit #{start},#{limit}")
    public List<UserFollowing> selectByUserId(@Param("userId") int userId, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select * from userfollowing where followingId=#{followingId} limit #{start},#{limit}")
    public List<UserFollowing> selectByFollowingId(@Param("followingId") int followingId, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select count(id) from userfollowing where userId = #{userId}")
    public int selectFollowingCnt(@Param("userId") int userId);

    @Select("select count(id) from userfollowing where followingId = #{userId}")
    public int selectFollowerCnt(@Param("userId") int userId);

    @Update("update userfollowing set isRead = 1 where userId=#{followerId} and followingId=#{userId}")
    public void updateRead(@Param("userId") int userId, @Param("followerId") int followerId);

    @SelectProvider(type = UserFollowingSql.class, method = "updateReadBatch")
    public void updateReadBatch(@Param("userId") int userId, @Param("followerIds") List<Integer> followerIds);

    @Select("select * from userfollowing where followingId=#{userId} and isRead=0 limit #{start},#{limit}")
    public List<UserFollowing> selectUnreadFollowerList(@Param("userId") int userId, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select count(id) from userfollowing where followingId=#{userId} and isRead=0")
    public int selectUnreadCnt(@Param("userId") int userId);

}
