package com.jdw.dao;

import com.jdw.pojo.User;

import java.sql.SQLException;

public class UserDAO extends BasicDAO<User> {
    public User queryUserByUserId(String username) throws SQLException {
        String sql = "select * from t_user where username = ?";
        return queryForOneList(sql, User.class, username);
    }

    public User queryUserByUserIdAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryForOneList(sql, User.class, username, password);
    }

    public int saveUser(User user) throws SQLException {
        String sql = "insert into t_user values(null,?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
