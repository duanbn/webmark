package com.dy.webmark.service;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.UserException;

public interface IUserService {
    
    public boolean checkEmailExist(String email);

    /**
     * 用户注册. 只需要邮箱和密码就可以进行注册. 邮箱必须不存在.
     * 
     * @param user
     * @throws UserException
     */
    public void regUser(User user) throws UserException;

    /**
     * 登录
     * 
     * @param email
     * @param password
     */
    public User login(String email, String password) throws UserException;

    /**
     * 设置用户昵称，此昵称必须是不存在的.
     * 
     * @param nickname
     * @throws UserException
     */
    public void setNickName(int id, String nickname) throws UserException;

    /**
     * 注销用户
     * 
     * @param id
     * @param password
     */
    public void deleteUser(int id, String password);

}
