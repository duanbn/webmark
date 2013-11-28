package com.dy.webmark.service;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.BizException;

public interface IFavoriteService {

    /**
     * 添加收录
     * 
     * @param favo
     * @throws BizException
     */
    public void addFavorite(Favorite favo) throws BizException;

    /**
     * 添加转录
     * 
     * @param favoId
     * @param userId
     * @param clipId
     * @throws BizException
     */
    public void reprintFavorite(int favoId, int userId, int clipId) throws BizException;

    /**
     * 查找收录
     * 
     * @param favoId
     * @return
     * @throws BizException
     */
    public Favorite getFavoriteById(int favoId) throws BizException;

    /**
     * 查找收录. 是否包含转录人列表
     * 
     * @param favoId
     * @param isReprintList
     * @return
     * @throws BizException
     */
    public Favorite getFavoriteById(int favoId, boolean isReprintList, boolean isCnt) throws BizException;

    /**
     * 人气加1
     * 
     * @param favoId
     * @throws BizException
     */
    public void incrPopluar(int favoId) throws BizException;

    /**
     * 喜欢加1
     * 
     * @param favoId
     * @throws BizException
     */
    public void incrLike(int favoId) throws BizException;

    /**
     * 转录加1
     * 
     * @param favoId
     * @throws BizException
     */
    public void incrReprint(int favoId) throws BizException;

}
