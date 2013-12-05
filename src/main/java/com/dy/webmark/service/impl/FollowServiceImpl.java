package com.dy.webmark.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.entity.UserFollowing;
import com.dy.webmark.entity.UserFollowingClip;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IFollowService;
import com.dy.webmark.service.IFollowingClipService;
import com.dy.webmark.service.IUserFollowingService;
import com.dy.webmark.service.IUserService;

@Service
public class FollowServiceImpl implements IFollowService {

    @Resource
    private IUserFollowingService userFollowingService;

    @Resource
    private IFollowingClipService followingClipService;

    @Resource
    private IUserService userService;

    @Resource
    private IFavoriteClipService clipService;

    @Override
    public List<User> getFollowingUser(int userId, int start, int limit) throws BizException {
        List<UserFollowing> followingList = userFollowingService.getFollowingList(userId, start, limit);
        List<Integer> userIds = new ArrayList<Integer>();
        for (UserFollowing userFollowing : followingList) {
            userIds.add(userFollowing.getFollowingId());
        }

        List<User> users = userService
                .getUserByIds(ArrayUtils.toPrimitive(userIds.toArray(new Integer[userIds.size()])));
        return users;
    }

    @Override
    public List<FavoriteClip> getFollowingClips(int userId, int start, int limit) {
        List<UserFollowingClip> followingClips = followingClipService.getFollowingClips(userId, start, limit);
        List<Integer> clipIds = new ArrayList<Integer>();
        for (UserFollowingClip followingClip : followingClips) {
            clipIds.add(followingClip.getFollowingClipId());
        }

        List<FavoriteClip> clips = clipService.getClipByIds(ArrayUtils.toPrimitive(clipIds.toArray(new Integer[clipIds
                .size()])));
        return clips;
    }
}
