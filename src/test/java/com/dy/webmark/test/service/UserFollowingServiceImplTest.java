package com.dy.webmark.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dy.webmark.entity.UserFollowing;
import com.dy.webmark.service.IUserFollowingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class UserFollowingServiceImplTest {

    @Resource
    private IUserFollowingService userFollowingService;

    @Test
    public void testFollowUser() throws Exception {
        userFollowingService.followUser(1, 2);
    }

    @Test
    public void testUnfollowUser() throws Exception {
        userFollowingService.unfollowUser(1, 2);
        userFollowingService.unfollowUser(1, 3);
    }

    @Test
    public void testGetFollowingCnt() {
        int cnt = userFollowingService.getFollowingCnt(1);
        System.out.println(cnt);
    }

    @Test
    public void testGetFollowerCnt() {
        int cnt = userFollowingService.getFollowerCnt(3);
        System.out.println(cnt);
    }

    @Test
    public void testGetFollowingList() {
        List<UserFollowing> rst = userFollowingService.getFollowingList(1, 0, 10);
        System.out.println(rst.size());
    }

    @Test
    public void testGetFollowerList() throws Exception {
        List<UserFollowing> rst = userFollowingService.getFollowerList(2, 0, 10);
        System.out.println(rst.size());

        Thread.sleep(1000);
    }

    @Test
    public void testGetUnreadFollowerList() {
        List<UserFollowing> rst = userFollowingService.getUnreadFollowerList(2, 0, 10);
        System.out.println(rst.size());
    }

    @Test
    public void testGetUnreadFollowerCnt() {
        int cnt = userFollowingService.getUnreadFollowerCnt(2);
        System.out.println(cnt);
    }

}
