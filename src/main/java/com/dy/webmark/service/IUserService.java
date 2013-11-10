package com.dy.webmark.service;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;

public interface IUserService {
    
    public User get(String email) throws BizException;
    
    public boolean checkEmailExist(String email);

    /**
     * 用户注册. 只需要邮箱和密码就可以进行注册. 邮箱必须不存在.
     * 
     * @param user
     * @throws BizException
     */
    public void regUser(User user) throws BizException;

    /**
     * 登录
     * 
     * @param email
     * @param password
     */
    public User login(String email, String password) throws BizException;

    /**
     * 设置用户昵称，此昵称必须是不存在的.
     * 
     * @param nickname
     * @throws BizException
     */
    public void setNickName(int id, String nickname) throws BizException;

    /**
     * 注销用户
     * 
     * @param id
     * @param password
     */
    public void deleteUser(int id, String password);

}
