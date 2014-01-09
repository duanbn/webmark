package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.BizException;

public interface IFavoriteService {
    
    public List<String> getScreenshot(int clipId);
    
    /**
     * 获取用户的收录数
     */
    public long getFavoCnt(int userId);

    /**
     * 根据优夹获取收录列表
     * 
     * @param clipId
     * @param start
     * @param limit
     * @return
     */
    public List<Favorite> getByClip(int clipId, int start, int limit);

    /**
     * 根据优夹获取收录列表
     * 
     * @param clipId
     * @param hasCnt 是否包含收录计数
     * @param start
     * @param limit
     * @return
     */
    public List<Favorite> getByClip(int clipId, boolean hasCnt, int start, int limit);

    /**
     * 生成网页缩略图
     * 
     * @return
     * @throws BizException
     */
    public String genSreentshot(String url) throws BizException;

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
    public Favorite getFavoriteById(int favoId, boolean hasReprintList, boolean hasCnt) throws BizException;

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
