package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;

public interface IFavoriteClipService {
    
    public FavoriteClip getByName(int userId, String clipName) throws BizException;

    public void addFavoriteClip(FavoriteClip clip) throws BizException;
    
    public List<FavoriteClip> getFavoriteClip(int userId);
    
}
