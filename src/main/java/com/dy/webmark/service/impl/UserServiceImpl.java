package com.dy.webmark.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dy.webmark.common.ErrorCode;
import com.dy.webmark.entity.FavoriteClip;
import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;
import com.dy.webmark.mapper.UserMapper;
import com.dy.webmark.service.IFavoriteClipService;
import com.dy.webmark.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    public static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private IFavoriteClipService clipService;

    @Override
    public User get(String email) throws BizException {
        User user = userMapper.getUserByEmail(email);
        if (user == null) {
            throw new BizException(ErrorCode.BIZ1002);
        }

        return user;
    }

    @Override
    public List<User> getUserByIds(int[] ids) {
        List<User> users = userMapper.getUserByIds(ids);

        if (users == null) {
            return Collections.emptyList();
        }

        return users;
    }

    @Override
    public boolean checkEmailExist(String email) {
        String s = userMapper.getEmail(email);
        return StringUtils.isNotBlank(s);
    }

    @Override
    public void regUser(User user) throws BizException {
        // 判断邮箱是否已经注册
        if (userMapper.getUserByEmail(user.getU_email()) != null) {
            throw new BizException(ErrorCode.BIZ1003);
        }

        try {
            // 加密用户密码
            user.setU_password(_md5(user.getU_password()));

            userMapper.insertUser(user);
            // 创建默认的优夹
            FavoriteClip defaultClip = new FavoriteClip();
            defaultClip.setFc_name("默认");
            defaultClip.setFc_userid(user.getU_id());
            defaultClip.setFc_isdefault(true);
            clipService.addFavoriteClip(defaultClip);

            if (LOG.isDebugEnabled()) {
                LOG.debug("register user done, " + user);
            }
        } catch (Exception e) {
            throw new BizException(ErrorCode.BIZ1001, e);
        }
    }

    @Override
    public User login(String email, String password) throws BizException {
        User user = userMapper.getUserByEmail(email);
        if (user == null) {
            throw new BizException(ErrorCode.BIZ1005);
        }

        if (!user.getU_password().equals(_md5(password))) {
            throw new BizException(ErrorCode.BIZ1006);
        }

        return user;
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
