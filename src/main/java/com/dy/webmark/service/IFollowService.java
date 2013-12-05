package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;

public interface IFollowService {

    /**
     * 获取关注人的列表
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<User> getFollowingUser(int userId, int start, int limit) throws BizException;

    /**
     * 获取关注优夹的列表
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<FavoriteClip> getFollowingClips(int userId, int start, int limit) throws BizException;

}
