package com.dy.webmark.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.service.IFavoriteClipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class FavoriteClipServiceTest {

    public static final Logger LOG = Logger.getLogger(FavoriteClipServiceTest.class);

    @Resource
    private IFavoriteClipService clipService;

    @Test
    public void testIncrFavoriteCnt() throws Exception {
        int clipId = 1;
        clipService.incrFavoCnt(clipId);
    }

    @Test
    public void testAdd() throws Exception {
        FavoriteClip clip = new FavoriteClip();
        clip.setUserId(2);
        clip.setName("我的优夹");
        clipService.addFavoriteClip(clip);
    }

    @Test
    public void testGetByNamePrefix() {
        List<FavoriteClip> clips = clipService.getByNamePrefix(2, "我的");
        for (FavoriteClip clip : clips) {
            LOG.info(clip);
        }
    }

    @Test
    public void testGetFavoriteClip() throws BizException {
        int clipId = 1;
        FavoriteClip clip = clipService.getById(clipId);
        LOG.info(clip);
    }

}
