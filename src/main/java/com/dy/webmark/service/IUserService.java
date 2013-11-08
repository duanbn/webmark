package com.dy.webmark.service;

import com.dy.webmark.entity.User;
import com.dy.webmark.exception.UserException;

public interface IUserService {

    public void regUser(User user) throws UserException;

    public User getUserById(int id) throws UserException;

    public User getUserByName(String name, String password) throws UserException;

    public User getUserByEmail(String email, String password) throws UserException;

    public void deleteUser(String name, String password);

}
