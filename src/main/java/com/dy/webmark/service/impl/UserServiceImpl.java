package com.dy.webmark.service.impl;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.ErrorCode;
import com.dy.webmark.exception.UserException;
import com.dy.webmark.mapper.UserMapper;
import com.dy.webmark.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    public static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean checkEmailExist(String email) {
        String s = userMapper.getEmail(email);
        return StringUtils.isNotBlank(s);
    }

    @Override
    public void regUser(User user) throws UserException {
        // 判断邮箱是否已经注册
        if (userMapper.getUserByEmail(user.getEmail()) != null) {
            throw new UserException(ErrorCode.USER_EMAIL_EXIST);
        }

        try {
            // 加密用户密码
            user.setPassword(_md5(user.getPassword()));

            userMapper.insertUser(user);

            if (LOG.isDebugEnabled()) {
                LOG.debug("register user done, " + user);
            }
        } catch (Exception e) {
            throw new UserException(ErrorCode.USER_REG_FAIL, e);
        }
    }

    @Override
    public User login(String email, String password) throws UserException {
        User user = userMapper.getUserByEmail(email);
        if (user == null) {
            throw new UserException(ErrorCode.EMAIL_NOT_EXIST);
        }

        if (!user.getPassword().equals(_md5(password))) {
            throw new UserException(ErrorCode.USER_PASSWORD_ERROR);
        }

        return user;
    }

    @Override
    public void setNickName(int id, String nickname) throws UserException {
        User user = userMapper.getUserByNickName(nickname);
        if (user != null) {
            throw new UserException(ErrorCode.USER_NICKNAME_EXIST);
        }

        userMapper.updateNickName(id, nickname);
    }

    @Override
    public void deleteUser(int id, String password) {
        userMapper.deleteUserById(id, _md5(password));
    }

    private String _md5(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("需要被加密的字符串不能为空");
        }

        try {
            return DigestUtils.md5Hex(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
