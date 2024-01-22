package com.example.library.service;

import com.example.library.entities.ShoppingCart;

public interface OrderService {
    void savedOrder(ShoppingCart cart);
    void acceptOrder(Long id);
    void cancelOrder(Long id);
}
