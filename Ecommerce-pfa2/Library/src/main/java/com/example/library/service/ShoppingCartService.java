package com.example.library.service;

import com.example.library.entities.CartItem;
import com.example.library.entities.Customer;
import com.example.library.entities.Product;
import com.example.library.entities.ShoppingCart;

import java.util.Set;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);
    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);
    ShoppingCart deleteItemFromCart(Product product, Customer customer);

}
