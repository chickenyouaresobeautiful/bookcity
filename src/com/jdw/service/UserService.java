package com.jdw.service;

import com.jdw.dao.UserDAO;
import com.jdw.pojo.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    //注册
    public void registerUser(User user) throws SQLException {
        userDAO.saveUser(user);
    }
    //登录
    public User login(String username,String password) throws SQLException {
        return userDAO.queryUserByUserIdAndPassword(username,password);
    }
    //判断用户名是否可用
    public boolean existsUsername(String username) throws SQLException {
        if (userDAO.queryUserByUserId(username) == null && username != null){
            return true;
        }else {
            return false;
        }
    }
}
