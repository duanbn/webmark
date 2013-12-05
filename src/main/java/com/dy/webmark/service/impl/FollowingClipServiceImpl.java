package com.dy.webmark.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.UserFollowingClip;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FollowingClipMapper;
import com.dy.webmark.service.IFollowingClipService;

@Service
public class FollowingClipServiceImpl implements IFollowingClipService {

    @Resource
    private FollowingClipMapper followingClipMapper;

    @Override
    public List<UserFollowingClip> getFollowingClips(int userId, int start, int limit) {
        List<UserFollowingClip> followingClips = followingClipMapper.selectFollowingClips(userId, start, limit);

        if (followingClips == null) {
            return Collections.emptyList();
        }

        return followingClips;
    }

    @Override
    public void followClip(int userId, int clipId) throws BizException {
        try {
            UserFollowingClip followingClip = new UserFollowingClip();
            followingClip.setFollowingClipId(clipId);
            followingClip.setUserId(userId);

            followingClipMapper.insert(followingClip);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }
    }

    @Override
    public void unFollowClip(int userId, int clipId) throws BizException {
        try {
            followingClipMapper.delete(userId, clipId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }
    }

}
