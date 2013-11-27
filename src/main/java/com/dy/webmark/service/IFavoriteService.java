package com.dy.webmark.service;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.BizException;

public interface IFavoriteService {

    public void addFavorite(Favorite favo) throws BizException;

    public Favorite getFavoriteById(int favoId) throws BizException;

    public void incrPopluar(int favoId) throws BizException;

    public void incrLike(int favoId) throws BizException;

    public void incrReprint(int favoId) throws BizException;

}
