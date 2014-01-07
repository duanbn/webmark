package com.dy.webmark.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.duanbn.common.util.ThreadPool;
import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.UserFollowing;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.UserFollowingMapper;
import com.dy.webmark.service.IUserFollowingService;

@Service
public class UserFollowingServiceImpl implements IUserFollowingService {

    public static final Logger LOG = Logger.getLogger(UserFollowingServiceImpl.class);

    @Resource
    private UserFollowingMapper userFollowingMapper;

    @Resource
    private ThreadPool threadPool;

    @Override
    public void followUser(int userId, int followingId) throws BizException {
        UserFollowing userFollowing = userFollowingMapper.selectOne(userId, followingId);
        if (userFollowing != null) {
            throw new BizException(ErrorCode.BIZ7001);
        }

        try {
            userFollowing = new UserFollowing();
            userFollowing.setUf_userid(userId);
            userFollowing.setUf_followingid(followingId);
            userFollowingMapper.insert(userFollowing);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }
    }

    @Override
    public void unfollowUser(int userId, int followingId) throws BizException {
        UserFollowing userFollowing = userFollowingMapper.selectOne(userId, followingId);
        if (userFollowing == null) {
            return;
        }

        try {
            userFollowingMapper.delete(userId, followingId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }

    }

    @Override
    public int getFollowingCnt(int userId) {
        return userFollowingMapper.selectFollowingCnt(userId);
    }

    @Override
    public int getFollowerCnt(int userId) {
        return userFollowingMapper.selectFollowerCnt(userId);
    }

    @Override
    public List<UserFollowing> getFollowingList(int userId, int start, int limit) {
        List<UserFollowing> rst = userFollowingMapper.selectByUserId(userId, start, limit);

        if (rst == null) {
            Collections.emptyList();
        }

        return rst;
    }

    @Override
    public List<UserFollowing> getFollowerList(final int userId, int start, int limit) {
        List<UserFollowing> rst = userFollowingMapper.selectByFollowingId(userId, start, limit);

        if (rst == null) {
            Collections.emptyList();
        }

        // 异步设置为已读
        final List<Integer> followerIds = new ArrayList<Integer>(rst.size());
        for (UserFollowing following : rst) {
            followerIds.add(following.getUf_followingid());
        }
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    setReaded(userId, followerIds);
                } catch (BizException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        });

        return rst;
    }

    @Override
    public List<UserFollowing> getUnreadFollowerList(int userId, int start, int limit) {
        List<UserFollowing> rst = userFollowingMapper.selectUnreadFollowerList(userId, start, limit);

        if (rst == null) {
            Collections.emptyList();
        }

        return rst;
    }

    @Override
    public int getUnreadFollowerCnt(int userId) {
        return userFollowingMapper.selectUnreadCnt(userId);
    }

    private final void setReaded(int userId, List<Integer> followerIds) throws BizException {
        try {
            userFollowingMapper.updateReadBatch(userId, followerIds);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }
    }

}
