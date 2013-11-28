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
        favo.setTitle("爱收藏1");
        favo.setKeyword("收藏，最爱");
        favo.setDescription("收藏你喜欢的url");
        favo.setUrl("http://www.ishoucang.com");
        favo.setClipId(1);
        favo.setUserId(1);
        favoService.addFavorite(favo);
    }

    @Test
    public void testReprintFavorite() throws Exception {
        int favoId = 8;
        int userId = 2;
        int clipId = 1;
        favoService.reprintFavorite(favoId, userId, clipId);
    }

    @Test
    public void testReprintList() throws Exception {
        int favoId = 8;
        Favorite favo = favoService.getFavoriteById(favoId, true, true);
        LOG.info(favo.getReprintUserList());
    }

    @Test
    public void testIncrLike() throws Exception {
        int favoId = 8;
        Favorite favo = favoService.getFavoriteById(favoId, false, true);
        favoService.incrLike(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId, false, true);
        Assert.assertEquals(favo.getCnt().getHowManyLike() + 1, favo1.getCnt().getHowManyLike());
    }

    @Test
    public void testIncrReprint() throws Exception {
        int favoId = 8;
        Favorite favo = favoService.getFavoriteById(favoId, false, true);
        favoService.incrReprint(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId, false, true);
        Assert.assertEquals(favo.getCnt().getHowManyReprint() + 1, favo1.getCnt().getHowManyReprint());
    }

    @Test
    public void testIncrPopluar() throws Exception {
        int favoId = 8;
        Favorite favo = favoService.getFavoriteById(favoId, false, true);
        favoService.incrPopluar(favoId);
        Favorite favo1 = favoService.getFavoriteById(favoId, false, true);
        Assert.assertEquals(favo.getCnt().getHowManyPopularity() + 1, favo1.getCnt().getHowManyPopularity());
    }

    @Test
    public void testGetFavoriteById() throws Exception {
        Favorite favo = favoService.getFavoriteById(1, true, true);
        LOG.info(favo);
    }

}
