package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.exception.BizException;

public interface IFavoriteClipService {
    
    /**
     * 获取此优夹里的收录数
     * @param clipId
     * @return
     */
    public long getFavoCnt(int clipId);
    
    /**
     * 获取用户的优夹数量.
     */
    public long getClipCnt(int userId);

    /**
     * 根据id获取优夹
     * 
     * @param clipIds
     * @return
     */
    public List<FavoriteClip> getClipByIds(int[] clipIds);

    /**
     * 优夹的收录数加1
     * 
     * @param favoClipId
     * @throws BizException
     */
    public void incrFavoCnt(int favoClipId) throws BizException;

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

    public FavoriteClip getById(int clipId) throws BizException;

    /**
     * 返回用户的所有优夹
     * 
     * @param userId
     * @return
     */
    public List<FavoriteClip> getFavoriteClip(int userId, boolean hasTitlePage, int start, int limit);
    
    public List<FavoriteClip> getFavoriteClip(int userId);

}
