package com.dy.webmark.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.ErrorCode;
import com.dy.webmark.exception.FavoriteException;
import com.dy.webmark.mapper.FavoriteMapper;
import com.dy.webmark.service.IFavoriteService;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceImpl.class);

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Favorite favo) throws FavoriteException {
        try {
            favoriteMapper.insertFavorite(favo);

            if (LOG.isDebugEnabled()) {
                LOG.debug("add favorite done, " + favo);
            }
        } catch (Exception e) {
            throw new FavoriteException(ErrorCode.FAVORITE_ADD_FAIL, e);
        }
    }

    @Override
    public Favorite getFavoriteById(int favoId) throws FavoriteException {
        Favorite favo = favoriteMapper.getFavoriteById(favoId);

        if (favo == null) {
            throw new FavoriteException(ErrorCode.FAVORITE_NOT_EXIST);
        }

        return favo;
    }

}
