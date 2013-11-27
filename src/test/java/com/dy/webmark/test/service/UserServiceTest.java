package com.dy.webmark.test.service;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class UserServiceTest {

    public static final Logger LOG = Logger.getLogger(UserServiceTest.class);

    @Resource
    private IUserService userService;

    private static final String NICKNAME = "duanbn";
    private static final String PASSWORD = "duanbn%1234";
    private static final String EMAIL = "duanbn@126.com";

    @Test
    public void testCheckEmail() {
        boolean isExis = userService.checkEmailExist(EMAIL);
        LOG.info(isExis);
    }

    @Test
    public void testGetUserByIds() throws BizException {
        int[] ids = new int[1];
        ids[0] = 1;
        List<User> users = userService.getUserByIds(ids);
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void testRegUser() throws BizException {
        User user = new User();
        user.setEmail("duanbn1@126.com");
        user.setPassword(PASSWORD);
        userService.regUser(user);

        try {
            userService.regUser(user);
        } catch (BizException e) {
            Assert.assertEquals(ErrorCode.BIZ1003, e.getEc());
        }
    }

    @Test
    public void testLogin() throws BizException {
        User user = null;
        try {
            user = userService.login("aa", PASSWORD);
        } catch (BizException e) {
            Assert.assertEquals(ErrorCode.BIZ1002, e.getEc());
        }

        try {
            user = userService.login(EMAIL, "1111");
        } catch (BizException e) {
            Assert.assertEquals(ErrorCode.BIZ1006, e.getEc());
        }

        user = userService.login(EMAIL, PASSWORD);
        userService.setNickName(user.getId(), NICKNAME);
    }

}
