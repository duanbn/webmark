package com.dy.webmark.service;

import com.dy.webmark.exception.BizException;

public interface IUserLoginService {

    public void saveUserLogin(String email, String sessionId, boolean isAutoLogin) throws BizException;

}
