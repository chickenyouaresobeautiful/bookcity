package com.jdw.test;

import com.jdw.pojo.User;
import com.jdw.service.UserService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserServiceTest {
    private UserService userService = new UserService();

    //注册
    @Test
    public void registerUser() throws SQLException {
        userService.registerUser(new User(null, "cmj", "123456", "cmj@qq.com"));
    }

    //登录
    @Test
    public void login() throws SQLException {
        if (userService.login("jdw","566320") == null){
            System.out.println("用户不存在");
        }else {
            System.out.println("登陆成功");
        }
    }

    @Test
    //判断用户名是否可用
    public void existsUsername() throws SQLException {
        if (userService.existsUsername("jdw")){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名不可用");
        }
    }
}
