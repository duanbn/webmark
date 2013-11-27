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
        favo.setClipId(1);
        favo.setUserId(1);
        favoService.addFavorite(favo);
    }

    @Test
    public void testIncrLike() throws Exception {
        int favoId = 1;
        Favorite favo = favoService.getFavoriteById(favoId);
        favoService.incrLike(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId);
        Assert.assertEquals(favo.getHowManyLike() + 1, favo1.getHowManyLike());
    }

    @Test
    public void testIncrReprint() throws Exception {
        int favoId = 1;
        Favorite favo = favoService.getFavoriteById(favoId);
        favoService.incrReprint(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId);
        Assert.assertEquals(favo.getHowManyReprint() + 1, favo1.getHowManyReprint());
    }

    @Test
    public void testIncrPopluar() throws Exception {
        int favoId = 1;
        Favorite favo = favoService.getFavoriteById(favoId);
        favoService.incrPopluar(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId);
        Assert.assertEquals(favo.getHowManyPopularity() + 1, favo1.getHowManyPopularity());
    }

    @Test
    public void testGetFavoriteById() throws Exception {
        Favorite favo = favoService.getFavoriteById(1);
        Assert.assertNotNull(favo);
        Assert.assertEquals("爱收藏", favo.getTitle());
    }

}
