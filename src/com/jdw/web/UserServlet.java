package com.jdw.web;

import com.google.gson.Gson;
import com.jdw.pojo.User;
import com.jdw.service.UserService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(username, password);
        if (login == null) {
            System.out.println("登陆失败");
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", req.getParameter("username"));
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登陆成功");
            req.getSession().setAttribute("user", login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = new User();
        System.out.println(WebUtils.copyParameterToBean(req.getParameterMap(), user));

        String s = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (s.equalsIgnoreCase(code)) {
            try {
                if (userService.existsUsername(username)) {
                    userService.registerUser(new User(null, username, password, email));
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                } else {
                    System.out.println("用户名" + username + "不可用");
                    req.setAttribute("msg", "用户名已存在");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.getSession().invalidate();
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
