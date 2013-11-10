package com.dy.webmark.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteMapper;
import com.dy.webmark.service.IFavoriteService;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceImpl.class);

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Favorite favo) throws BizException {
        try {
            favoriteMapper.insertFavorite(favo);

            if (LOG.isDebugEnabled()) {
                LOG.debug("add favorite done, " + favo);
            }
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2001, e);
        }
    }

    @Override
    public Favorite getFavoriteById(int favoId) throws BizException {
        Favorite favo = favoriteMapper.getFavoriteById(favoId);

        if (favo == null) {
            throw new BizException(ErrorCode.BIZ2002);
        }

        return favo;
    }

}
