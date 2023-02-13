package com.jdw.test;

import com.jdw.dao.UserDAO;
import com.jdw.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void queryUserByUserId() throws SQLException {
        UserDAO userDAO = new UserDAO();
        if (userDAO.queryUserByUserId("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名不可用");
        }
    }

    @Test
    public void queryUserByUserIdAndPassword() throws SQLException {
        UserDAO userDAO = new UserDAO();
        if (userDAO.queryUserByUserIdAndPassword("admin","admin") == null) {
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.saveUser(new User(null,"jdw","566320","2638502607@qq.com"));
    }
}
