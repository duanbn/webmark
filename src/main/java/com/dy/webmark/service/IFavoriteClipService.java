package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;

public interface IFavoriteClipService {

    public void addFavoriteClip(FavoriteClip clip) throws BizException;
    
    public List<FavoriteClip> getFavoriteClip(int userId);

}
