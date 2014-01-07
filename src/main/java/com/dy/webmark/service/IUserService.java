package com.dy.webmark.service;

import java.util.List;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.BizException;

public interface IUserService {

    /**
     * 根据邮箱查找用户
     * 
     * @param email
     * @return
     * @throws BizException
     */
    public User get(String email) throws BizException;

    /**
     * 根据用户id查找用户
     * 
     * @param ids
     * @return
     */
    public List<User> getUserByIds(int[] ids);

    /**
     * 检查邮箱是否已经存在
     * 
     * @param email
     * @return
     */
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
     * 注销用户
     * 
     * @param id
     * @param password
     */
    public void deleteUser(int id, String password);

}
