package com.jdw.web;

import com.jdw.pojo.Cart;
import com.jdw.pojo.User;
import com.jdw.service.OrderService;
import com.jdw.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
