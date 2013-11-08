package com.dy.webmark.test.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.service.IFavoriteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class FavoriteServiceTest {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceTest.class);

    @Resource
    private IFavoriteService favoService;

    @Test
    public void testAddFavorite() throws Exception {
        Favorite favo = new Favorite();
        favo.setTitle("爱收藏");
        favo.setKeyword("收藏，最爱");
        favo.setDescription("收藏你喜欢的url");
        favo.setUrl("http://www.ishoucang.com");
        favo.setUserId(1);
        favoService.addFavorite(favo);
    }

    @Test
    public void testGetFavoriteById() throws Exception {
        Favorite favo = favoService.getFavoriteById(1);
        Assert.assertNotNull(favo);
        Assert.assertEquals("爱收藏", favo.getTitle());
    }

}
