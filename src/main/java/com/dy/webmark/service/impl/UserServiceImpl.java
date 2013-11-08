package com.dy.webmark.service.impl;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void regUser(User user) throws UserException {
        // 判断用户名是否已经注册
        if (userMapper.getUserByName(user.getName()) != null) {
            throw new UserException(ErrorCode.USER_NAME_EXIST);
        }
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
        
        throw new RuntimeException();
    }

    @Override
    public User getUserById(int id) throws UserException {
        User user = userMapper.getUserById(id);

        if (user == null) {
            throw new UserException(ErrorCode.USER_NOT_EXIST);
        }

        return user;
    }

    @Override
    public User getUserByName(String name, String password) throws UserException {
        User user = userMapper.getUserByNameWithPwd(name, _md5(password));

        if (user == null) {
            throw new UserException(ErrorCode.USER_NOT_EXIST);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email, String password) throws UserException {
        User user = userMapper.getUserByEmailWithPwd(email, _md5(password));

        if (user == null) {
            throw new UserException(ErrorCode.USER_NOT_EXIST);
        }

        return user;
    }

    @Override
    @Transactional
    public void deleteUser(String name, String password) {
        userMapper.deleteUserByName(name, _md5(password));
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
