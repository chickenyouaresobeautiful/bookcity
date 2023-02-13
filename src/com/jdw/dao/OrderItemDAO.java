package com.jdw.dao;

import com.jdw.pojo.Order;
import com.jdw.pojo.OrderItem;

import java.sql.SQLException;

public class OrderItemDAO extends BasicDAO<OrderItem>{
    public int saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item values(?,?,?,?,?,?)";
        return update(sql, orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    public OrderItem queryOrderItemsByOrderId(String orderId) throws SQLException {
        String sql = "select * from t_order_item where order_id = ?";
        return queryForOneList(sql,OrderItem.class,orderId);
    }
}
