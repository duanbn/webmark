package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;

public interface IFavoriteClipService {

    /**
     * 根据优夹名称的前缀查找优夹列表
     * 
     * @param userId
     * @param clipNamePrefix
     * @return
     */
    public List<FavoriteClip> getByNamePrefix(int userId, String clipNamePrefix);

    /**
     * 根据优夹名称查找优夹
     * 
     * @param userId
     * @param clipName
     * @return
     * @throws BizException
     */
    public FavoriteClip getByName(int userId, String clipName) throws BizException;

    /**
     * 添加优夹
     * 
     * @param clip
     * @throws BizException
     */
    public void addFavoriteClip(FavoriteClip clip) throws BizException;

    /**
     * 返回用户的所有优夹
     * 
     * @param userId
     * @return
     */
    public List<FavoriteClip> getFavoriteClip(int userId);

}
