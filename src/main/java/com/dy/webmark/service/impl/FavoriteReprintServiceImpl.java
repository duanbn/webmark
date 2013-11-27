package com.dy.webmark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.FavoriteReprint;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.FavoriteReprintMapper;
import com.dy.webmark.service.IFavoriteReprintService;

@Service
public class FavoriteReprintServiceImpl implements IFavoriteReprintService {

    @Resource
    private FavoriteReprintMapper favoReprintMapper;

    @Override
    public void addFavoriteReprint(FavoriteReprint fr) throws BizException {
        try {
            favoReprintMapper.insertFavoriteReprint(fr);
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ6001, e);
        }
    }

}
