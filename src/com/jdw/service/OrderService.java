package com.jdw.service;

import com.jdw.dao.BookDAO;
import com.jdw.dao.OrderDAO;
import com.jdw.dao.OrderItemDAO;
import com.jdw.pojo.*;

import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({"all"})
public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    private BookDAO bookDAO = new BookDAO();

    public String createOrder(Cart cart,Integer userId) throws Exception {
        String orderId = UUID.randomUUID().toString();
        orderDAO.saveOrder(new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId));
        Set<Map.Entry<Integer, CartItem>> entries = cart.getItems().entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDAO.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDAO.selectBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDAO.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    public List<Order> showAllOrders() throws SQLException {
        return orderDAO.queryOrders();
    }

    public void sendOrder(String orderId) throws SQLException {
        orderDAO.changeOrderStatus(orderId, 1);
    }

    public OrderItem showOrderDetail(String orderId) throws SQLException {
        return orderItemDAO.queryOrderItemsByOrderId(orderId);
    }

    public Order showMyOrders(Integer userId) throws SQLException {
        return orderDAO.queryOrderByUserId(userId);
    }

    public void receiverOrder(String orderId) throws SQLException {
        orderDAO.changeOrderStatus(orderId,2);
    }
}
