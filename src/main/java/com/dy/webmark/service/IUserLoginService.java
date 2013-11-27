package com.dy.webmark.service;

import com.dy.webmark.entity.UserLogin;
import com.dy.webmark.exception.BizException;

public interface IUserLoginService {

    /**
     * 获取用户登录信息
     * 
     * @param email
     * @param sessionId
     * @return
     */
    public UserLogin getUserLogin(String email, String sessionId);

    /**
     * 更新用户登录信息
     * 
     * @param email
     * @param sessionId
     * @param isAutoLogin
     * @throws BizException
     */
    public void updateUserLogin(String email, String sessionId, boolean isAutoLogin) throws BizException;

}
