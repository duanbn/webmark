package com.dy.webmark.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteClipMapper;
import com.dy.webmark.service.IFavoriteClipService;

@Service
public class FavoriteClipServiceImpl implements IFavoriteClipService {

    @Resource
    private FavoriteClipMapper favoriteClipMapper;

    private static final List<FavoriteClip> EMPTY = new ArrayList<FavoriteClip>();

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
        if (favoriteClipMapper.selectByName(clip.getUserId(), clip.getName()) != null) {
            throw new BizException(ErrorCode.BIZ4003);
        }

        try {
            favoriteClipMapper.insert(clip);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ4001);
        }
    }

    @Override
    public List<FavoriteClip> getFavoriteClip(int userId) {
        List<FavoriteClip> clips = favoriteClipMapper.selectByUserId(userId);

        if (clips != null) {
            return clips;
        }

        return EMPTY;
    }
}
