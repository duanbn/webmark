package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.UserFollowingClip;
import com.dy.webmark.exception.BizException;

public interface IFollowingClipService {

    /**
     * 获取关注的优夹
     * 
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    public List<UserFollowingClip> getFollowingClips(int userId, int start, int limit);

    /**
     * 关注一个优夹
     * 
     * @param userId
     * @param clipId
     * @throws BizException
     */
    public void followClip(int userId, int clipId) throws BizException;

    /**
     * 取消关注优夹
     * 
     * @param userId
     * @param clipId
     * @throws BizException
     */
    public void unFollowClip(int userId, int clipId) throws BizException;

}
