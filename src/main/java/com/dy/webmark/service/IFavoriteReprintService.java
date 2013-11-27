package com.dy.webmark.service;

import com.dy.webmark.entity.FavoriteReprint;
import com.dy.webmark.exception.BizException;

/**
 * 转录
 * 
 * @author duanbn
 * 
 */
public interface IFavoriteReprintService {

    /**
     * 添加一条转录信息
     * 
     * @param fr
     * @throws BizException
     */
    public void addFavoriteReprint(FavoriteReprint fr) throws BizException;

}
