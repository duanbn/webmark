package com.dy.webmark.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteClipMapper;
import com.dy.webmark.mapper.FavoriteMapper;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IFavoriteService;

@Service
public class FavoriteClipServiceImpl implements IFavoriteClipService {

    public static final Logger LOG = Logger.getLogger(FavoriteClipServiceImpl.class);

    @Resource
    private IFavoriteService favoService;
    @Resource
    private FavoriteClipMapper favoriteClipMapper;

    @Resource
    private FavoriteMapper favoMapper;

    private static final List<FavoriteClip> EMPTY = new ArrayList<FavoriteClip>();

    @Override
    public long getFavoCnt(int clipId) {
        return favoMapper.selectCntByClipId(clipId);
    }

    @Override
    public long getClipCnt(int userId) {
        return favoriteClipMapper.selectCnt(userId);
    }

    @Override
    public List<FavoriteClip> getClipByIds(int[] clipIds) {
        List<FavoriteClip> clips = favoriteClipMapper.selectByIds(clipIds);

        if (clips == null) {
            return Collections.emptyList();
        }

        return clips;
    }

    @Override
    public void incrFavoCnt(int favoClipId) throws BizException {
        try {
            favoriteClipMapper.incrFavoCnt(favoClipId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ5003);
        }
    }

    @Override
    public List<FavoriteClip> getByNamePrefix(int userId, String clipNamePrefix) {
        List<FavoriteClip> clips = favoriteClipMapper.selectByNamePrefix(userId, clipNamePrefix);

        if (clips != null) {
            return clips;
        }

        return EMPTY;
    }

    @Override
    public FavoriteClip getByName(int userId, String clipName) throws BizException {
        FavoriteClip clip = favoriteClipMapper.selectByName(userId, clipName);

        if (clip == null) {
            throw new BizException(ErrorCode.BIZ4002);
        }

        return clip;
    }

    @Override
    public void addFavoriteClip(FavoriteClip clip) throws BizException {
        if (favoriteClipMapper.selectByName(clip.getFc_userid(), clip.getFc_name()) != null) {
            throw new BizException(ErrorCode.BIZ4003);
        }

        try {
            favoriteClipMapper.insert(clip);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BizException(ErrorCode.BIZ4001);
        }
    }

    @Override
    public FavoriteClip getById(int clipId) throws BizException {
        FavoriteClip clip = favoriteClipMapper.selectById(clipId);
        if (clip == null) {
            throw new BizException(ErrorCode.BIZ4002);
        }
        return clip;
    }

    @Override
    public List<FavoriteClip> getFavoriteClip(int userId) {
        List<FavoriteClip> clips = favoriteClipMapper.selectByUserId(userId, 0, Integer.MAX_VALUE);

        if (clips != null) {
            return clips;
        }

        return EMPTY;
    }

    @Override
    public List<FavoriteClip> getFavoriteClip(int userId, boolean hasTitlePage, int start, int limit) {
        List<FavoriteClip> clips = favoriteClipMapper.selectByUserId(userId, start, limit);
        if (clips != null) {
            if (hasTitlePage) {
                for (FavoriteClip clip : clips) {
                    List<String> titlepages = new ArrayList<String>();
                    for (String titlepage : favoService.getScreenshot(clip.getFc_id())) {
                        if (StringUtils.isNotBlank(titlepage)) {
                            titlepages.add(titlepage.substring(0, titlepage.lastIndexOf(".")) + "_thum.jpg");
                        }
                    }
                    clip.setFc_titlepage(titlepages);
                }
            }
            return clips;
        }

        return EMPTY;
    }
}
