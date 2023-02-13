package com.jdw.dao;

import com.jdw.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends BasicDAO<Order>{
    public int saveOrder(Order order) throws SQLException {
        String sql = "insert into t_order values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    public List<Order> queryOrders() throws SQLException {
        String sql = "select * from t_order";
        return queryForList(sql, Order.class);
    }

    public int changeOrderStatus(String orderId,Integer status) throws SQLException {
        String sql = "update t_order set status = ? where order_id = ?";
        return update(sql, status, orderId);
    }

    public Order queryOrderByUserId(Integer userId) throws SQLException {
        String sql = "select * from t_order where user_id = ?";
        return queryForOneList(sql,Order.class,userId);
    }

}
