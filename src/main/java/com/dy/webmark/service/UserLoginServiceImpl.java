package com.dy.webmark.service;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.UserLogin;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.UserLoginMapper;

@Service
public class UserLoginServiceImpl implements IUserLoginService {

    public static final Logger LOG = Logger.getLogger(UserLoginServiceImpl.class);

    @Resource
    private UserLoginMapper userLoginMapper;

    @Override
    public void saveUserLogin(String email, String sessionId, boolean isAutoLogin) throws BizException {
        try {
            UserLogin login = userLoginMapper.getByEmail(email);
            if (login == null) {
                login = new UserLogin();
                login.setEmail(email);
                login.setSessionId(sessionId);
                login.setAutoLogin(isAutoLogin);
                userLoginMapper.insert(login);
            } else {
                login.setSessionId(sessionId);
                login.setAutoLogin(isAutoLogin);
                login.setLoginTime(new Timestamp(System.currentTimeMillis()));
                userLoginMapper.update(login);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BizException(ErrorCode.BIZ3001);
        }
    }

}
