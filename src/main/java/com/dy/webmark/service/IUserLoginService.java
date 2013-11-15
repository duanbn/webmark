package com.dy.webmark.service;

import com.dy.webmark.entity.UserLogin;
import com.dy.webmark.exception.BizException;

public interface IUserLoginService {
    
    public UserLogin getUserLogin(String email, String sessionId);

    public void updateUserLogin(String email, String sessionId, boolean isAutoLogin) throws BizException;

}
