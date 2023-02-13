package com.jdw.pojo;

import java.util.*;

public class Cart {
    private Integer totalCount;
    private Double totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Integer getTotalCount() {
        totalCount = 0;
        Set<Map.Entry<Integer, CartItem>> entries = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Double getTotalPrice() {
        totalPrice = 0.0;
        Set<Map.Entry<Integer, CartItem>> entries = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem cartItem){
        CartItem cartItem1 = items.get(cartItem.getId());
        if (cartItem1 == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            cartItem1.setCount(cartItem1.getCount() + 1);
            cartItem1.setTotalPrice(cartItem1.getPrice() * cartItem1.getCount());
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        cartItem.setCount(count);
        cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
