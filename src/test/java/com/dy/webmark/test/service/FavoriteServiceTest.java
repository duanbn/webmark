package com.dy.webmark.test.service;

import java.util.List;

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
    public void testGetFavoriteByClip() {
        int userId = 1;
        int clipId = 1;
        int start = 0;
        int limit = 30;
        List<Favorite> rst = favoService.getByClip(userId, clipId, false, start, limit);
        LOG.info(rst);
    }

    @Test
    public void testAddFavorite() throws Exception {
        String url = "http://www.lietou.com";
        String fileName = favoService.genSreentshot(url);

        Favorite favo = new Favorite();
        favo.setClipId(1);
        favo.setDescription("猎聘网为中高端人才提供超过500万条高薪职位信息,70000多位猎头在线为您服务,覆盖40多个行业,发布世界500强企业最新招聘信息。找猎头上猎聘,你就是精英！");
        favo.setKeyword("猎头网,猎聘猎头网,找猎头,找工作,猎聘网,高端招聘");
        favo.setTitle("猎聘网 - 中高端人才求职、找工作，首选招聘平台！");
        favo.setUrl(url);
        favo.setUserId(1);
        favo.setScreenshot(fileName);

        favoService.addFavorite(favo);
    }

    @Test
    public void testReprintFavorite() throws Exception {
        int favoId = 12;
        int userId = 2;
        int clipId = 1;
        favoService.reprintFavorite(favoId, userId, clipId);
    }

    @Test
    public void testReprintList() throws Exception {
        int favoId = 12;
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
