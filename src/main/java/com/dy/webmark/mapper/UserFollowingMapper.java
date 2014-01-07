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

    @Select("select * from userfollowing where uf_userid=#{uf_userid} and uf_followingid=#{uf_followingid}")
    public UserFollowing selectOne(@Param("uf_userid") int uf_userid, @Param("uf_followingid") int uf_followingid);

    @Insert("insert into userfollowing(uf_userid, uf_followingid, uf_followtime) values(#{uf_userid}, #{uf_followingid}, #{uf_followtime})")
    public void insert(UserFollowing userFollowing);

    @Delete("delete from userfollowing where uf_userid=#{uf_userid} and uf_followingid=#{uf_followingid}")
    public void delete(@Param("uf_userid") int uf_userid, @Param("uf_followingid") int uf_followingid);

    @Select("select * from userfollowing where uf_userid=#{uf_userid} limit #{start},#{limit}")
    public List<UserFollowing> selectByUserId(@Param("uf_userid") int uf_userid, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select * from userfollowing where uf_followingid=#{uf_followingid} limit #{start},#{limit}")
    public List<UserFollowing> selectByFollowingId(@Param("uf_followingid") int uf_followingid, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select count(uf_id) from userfollowing where uf_userid = #{uf_userid}")
    public int selectFollowingCnt(@Param("uf_userid") int uf_userid);

    @Select("select count(uf_id) from userfollowing where uf_followingid = #{uf_userid}")
    public int selectFollowerCnt(@Param("uf_userid") int uf_userid);

    @Update("update userfollowing set uf_isread = 1 where uf_userid=#{uf_followerid} and uf_followingid=#{uf_userid}")
    public void updateRead(@Param("uf_userid") int uf_userid, @Param("uf_followerid") int uf_followerid);

    @SelectProvider(type = UserFollowingSql.class, method = "updateReadBatch")
    public void updateReadBatch(@Param("uf_userid") int uf_userid, @Param("uf_followerids") List<Integer> uf_followerids);

    @Select("select * from userfollowing where uf_followingid=#{uf_userid} and uf_isread=0 limit #{start},#{limit}")
    public List<UserFollowing> selectUnreadFollowerList(@Param("uf_userid") int uf_userid, @Param("start") int start,
            @Param("limit") int limit);

    @Select("select count(uf_id) from userfollowing where uf_followingid=#{uf_userid} and uf_isread=0")
    public int selectUnreadCnt(@Param("uf_userid") int uf_userid);

}
