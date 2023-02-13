package com.jdw.test;

import com.jdw.pojo.Cart;
import com.jdw.pojo.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Cart cart = new Cart();
    @Test
    void addItem() {
        cart.addItem(new CartItem(1,"java从入门到放弃",2,30.0,60.0));
        cart.addItem(new CartItem(2,"c语言",1,40.0,40.0));
        cart.addItem(new CartItem(3,"c++",3,30.0,90.0));
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        cart.addItem(new CartItem(1,"java从入门到放弃",2,30.0,60.0));
        cart.addItem(new CartItem(2,"c语言",1,40.0,40.0));
        cart.addItem(new CartItem(3,"c++",3,30.0,90.0));
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        cart.addItem(new CartItem(1,"java从入门到放弃",2,30.0,60.0));
        cart.addItem(new CartItem(2,"c语言",1,40.0,40.0));
        cart.addItem(new CartItem(3,"c++",3,30.0,90.0));
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        cart.addItem(new CartItem(1,"java从入门到放弃",2,30.0,60.0));
        cart.addItem(new CartItem(2,"c语言",1,40.0,40.0));
        cart.addItem(new CartItem(3,"c++",3,30.0,90.0));
        System.out.println(cart);
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}