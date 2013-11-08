package com.dy.webmark.test.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.ErrorCode;
import com.dy.webmark.exception.UserException;
import com.dy.webmark.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class UserServiceTest {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceTest.class);

    @Resource
    private IUserService userService;

    private static final String NAME = "duanbn";
    private static final String PASSWORD = "duanbn%1234";
    private static final String EMAIL = "duanbn@126.com";
    
    @Test
    public void testTransaction() throws Exception {
        User user = new User();
        user.setEmail("333");
        user.setName("333");
        user.setPassword(PASSWORD);
        userService.regUser(user);
    }

    @Test
    public void testRegUser() throws UserException {
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        userService.regUser(user);

        try {
            user.setName("duanbn1");
            userService.regUser(user);
        } catch (UserException e) {
            Assert.assertEquals(ErrorCode.USER_EMAIL_EXIST, e.getEc());
        }

        try {
            user.setEmail("duanbn1@126.com");
            userService.regUser(user);
        } catch (UserException e) {
            Assert.assertEquals(ErrorCode.USER_NAME_EXIST, e.getEc());
        }
    }

    @Test
    public void testGetUserById() throws UserException {
        User user = userService.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("duanbn", user.getName());
    }

    @Test
    public void testGetUserByName() throws UserException {
        User user = userService.getUserByName(NAME, PASSWORD);
        Assert.assertNotNull(user);
        Assert.assertEquals("duanbn", user.getName());
    }

    @Test
    public void testGetUserByEmail() throws UserException {
        User user = userService.getUserByEmail("duanbn@126.com", "duanbn%1234");
        Assert.assertNotNull(user);
        Assert.assertEquals("duanbn", user.getName());
    }

}
