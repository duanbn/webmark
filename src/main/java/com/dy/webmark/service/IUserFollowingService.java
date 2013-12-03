package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.UserFollowing;
import com.dy.webmark.exception.BizException;

/**
 * 人脉
 * 
 * @author duanbn
 * 
 */
public interface IUserFollowingService {

    /**
     * 关注
     * 
     * @param userId
     * @param followingId
     * @throws BizException
     */
    public void followUser(int userId, int followingId) throws BizException;

    /**
     * 取消关注
     * 
     * @param userId
     * @param followingId
     * @throws BizException
     */
    public void unfollowUser(int userId, int followingId) throws BizException;

    /**
     * 获取关注数
     * 
     * @param userId
     * @return
     */
    public int getFollowingCnt(int userId);

    /**
     * 获取粉丝数
     * 
     * @param userId
     * @return
     */
    public int getFollowerCnt(int userId);

    /**
     * 获取关注列表
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<UserFollowing> getFollowingList(int userId, int start, int limit);

    /**
     * 获取粉丝列表
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<UserFollowing> getFollowerList(int userId, int start, int limit);

    /**
     * 获取未读的粉丝列表
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<UserFollowing> getUnreadFollowerList(int userId, int start, int limit);

    /**
     * 获取未读的关注数
     * 
     * @param userId
     * @return
     */
    public int getUnreadFollowerCnt(int userId);

}
