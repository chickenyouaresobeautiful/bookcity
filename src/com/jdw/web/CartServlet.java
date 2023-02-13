package com.jdw.web;

import com.google.gson.Gson;
import com.jdw.pojo.Book;
import com.jdw.pojo.Cart;
import com.jdw.pojo.CartItem;
import com.jdw.service.BookService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookService();

    protected void ajaxAddToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.selectBookById(id);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setName(book.getName());
        cartItem.setCount(1);
        cartItem.setPrice(book.getPrice());
        cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        req.getSession().setAttribute("cart", cart);

        Map<String,Object> map = new HashMap<>();
        Object totalCount = map.put("totalCount", cart.getTotalCount());
        Object lastName = map.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }

    protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.selectBookById(id);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setName(book.getName());
        cartItem.setCount(1);
        cartItem.setPrice(book.getPrice());
        cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        req.getSession().setAttribute("cart", cart);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        cart.updateCount(id, count);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
