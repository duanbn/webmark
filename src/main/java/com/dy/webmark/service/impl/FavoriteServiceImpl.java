package com.dy.webmark.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.Favorite;
import com.dy.webmark.entity.FavoriteCnt;
import com.dy.webmark.entity.FavoriteReprint;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteCntMapper;
import com.dy.webmark.mapper.FavoriteMapper;
import com.dy.webmark.mapper.FavoriteReprintMapper;
import com.dy.webmark.service.IFavoriteService;
import com.dy.webmark.service.IUserService;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    public static final Logger LOG = Logger.getLogger(FavoriteServiceImpl.class);

    @Resource
    private FavoriteMapper favoMapper;
    @Resource
    private FavoriteReprintMapper favoReprintMapper;
    @Resource
    private FavoriteCntMapper favoCntMapper;

    @Resource
    private IUserService userService;

    @Override
    public void addFavorite(Favorite favo) throws BizException {
        try {
            favoMapper.insertFavorite(favo);
            FavoriteCnt cnt = new FavoriteCnt();
            cnt.setFavoId(favo.getId());
            favoCntMapper.addFavoriteCnt(cnt);

            if (LOG.isDebugEnabled()) {
                LOG.debug("add favorite done, " + favo);
            }
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2001, e);
        }
    }

    @Override
    public void reprintFavorite(int favoId, int userId, int clipId) throws BizException {
        FavoriteReprint fr = favoReprintMapper.getFavoriteReprint(favoId, userId);
        if (fr != null) {
            throw new BizException(ErrorCode.BIZ6002);
        }

        Favorite favo = getFavoriteById(favoId);

        // 添加转录
        Favorite reprintFavo = new Favorite();
        reprintFavo.setReprint(true);
        reprintFavo.setClipId(clipId);
        reprintFavo.setUserId(userId);
        reprintFavo.setDescription(favo.getDescription());
        reprintFavo.setKeyword(favo.getKeyword());
        reprintFavo.setTitle(favo.getTitle());
        reprintFavo.setUrl(favo.getUrl());
        addFavorite(reprintFavo);

        // 添加转录信息
        fr = new FavoriteReprint();
        fr.setClipId(clipId);
        fr.setFavoId(favoId);
        fr.setUserId(userId);
        favoReprintMapper.insertFavoriteReprint(fr);

        // 被转录的收录数加1
        incrReprint(favoId);
    }

    @Override
    public Favorite getFavoriteById(int favoId) throws BizException {
        return getFavoriteById(favoId, false, false);
    }

    @Override
    public Favorite getFavoriteById(int favoId, boolean isReprintList, boolean isCnt) throws BizException {
        Favorite favo = favoMapper.getFavoriteById(favoId);

        if (favo == null) {
            throw new BizException(ErrorCode.BIZ2002);
        }

        if (isReprintList) {
            List<FavoriteReprint> reprints = favoReprintMapper.getFavoriteReprintList(favoId);
            if (reprints != null && !reprints.isEmpty()) {
                int[] userIds = new int[reprints.size()];
                for (int i = 0; i < reprints.size(); i++) {
                    userIds[i] = reprints.get(i).getUserId();
                }

                List<User> users = userService.getUserByIds(userIds);

                favo.setReprintUserList(users);
            } else {
                favo.setReprintUserList(new ArrayList<User>());
            }
        }

        if (isCnt) {
            FavoriteCnt favoCnt = favoCntMapper.get(favoId);
            favo.setCnt(favoCnt);
        }

        return favo;
    }

    @Override
    public void incrPopluar(int favoId) throws BizException {
        try {
            favoCntMapper.incrPopluar(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2004, e);
        }
    }

    @Override
    public void incrLike(int favoId) throws BizException {
        try {
            favoCntMapper.incrLike(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2003, e);
        }
    }

    @Override
    public void incrReprint(int favoId) throws BizException {
        try {
            favoCntMapper.incrReprint(favoId);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ2005, e);
        }
    }

}
