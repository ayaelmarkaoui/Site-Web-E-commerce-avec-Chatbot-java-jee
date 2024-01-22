package com.example.library.service.Implem;

import com.example.library.entities.CartItem;
import com.example.library.entities.Customer;
import com.example.library.entities.Product;
import com.example.library.entities.ShoppingCart;
import com.example.library.repositories.CartItemRepository;
import com.example.library.repositories.ShoppingCartRepository;
import com.example.library.service.ShoppingCartService;
//import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private CartItemRepository itemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
        }
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItems == null) {
            cartItems = new HashSet<>();
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }

        } else {
            if (cartItem == null) {
                    cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setTotalPrice(quantity * product.getCostPrice());
                    cartItem.setQuantity(quantity);
                    cartItem.setCart(cart);
                    cartItems.add(cartItem);
                    itemRepository.save(cartItem);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getCostPrice()));
                    itemRepository.save(cartItem);
                }

            }
        cart.setCartItem(cartItems);
        int totalItems = totalItems(cart.getCartItem());
        double totalPrice = totalPrice(cart.getCartItem());
        cart.setTotalItems(totalItems);
        cart.setTotalPrices(totalPrice);
        cart.setCustomer(customer);

            return shoppingCartRepository.save(cart);
    }
            private CartItem findCartItem (Set<CartItem> cartItems, Long productId){
                if (cartItems == null) {
                    return null;
                }
                CartItem cartItem = null;
                for (CartItem item : cartItems) {
                    if (item.getProduct().getId() == productId) {
                        cartItem = item;
                    }
                }
                return cartItem;
            }
            private int totalItems(Set<CartItem> cartItems){
                int totalItems = 0;
                for (CartItem item : cartItems){
                    totalItems += item.getQuantity();
                }
                return totalItems;
            }
            private double totalPrice(Set<CartItem> cartItems){
               double totalPrice = 0;
                for (CartItem item : cartItems){
                    totalPrice += item.getTotalPrice();
                }
                return totalPrice;
            }
     @Override
     public ShoppingCart updateItemInCart(Product product, int quantity, Customer customer){
         ShoppingCart cart = customer.getShoppingCart();

         Set<CartItem> cartItems = cart.getCartItem();

         CartItem item = findCartItem(cartItems, product.getId());
         item.setQuantity(quantity);
         item.setTotalPrice(quantity * product.getCostPrice());
         itemRepository.save(item);
         int totaItems = totalItems(cartItems);
         double totaPrice = totalPrice(cartItems);
         cart.setTotalPrices(totaPrice);
         cart.setTotalItems(totaItems);
         return shoppingCartRepository.save(cart);
     }
     @Override
     public ShoppingCart deleteItemFromCart(Product product, Customer customer){
           ShoppingCart cart = customer.getShoppingCart();
           Set<CartItem> cartItems = cart.getCartItem();

           CartItem item = findCartItem(cartItems, product.getId());
           cartItems.remove(item);
           itemRepository.delete(item);
         int totaItems = totalItems(cartItems);
         double totaPrice = totalPrice(cartItems);
         cart.setCartItem(cartItems);
         cart.setTotalItems(totaItems);
         cart.setTotalPrices(totaPrice);
         return shoppingCartRepository.save(cart);
     }

     private CartItem findCartItems(Set<CartItem> cartItems, Long prouctId){
        if (cartItems == null){
            return null;
        }
        CartItem cartItem = null;
        for (CartItem item : cartItems){
            if (item.getProduct().getId() == prouctId){
                cartItem = item;

            }
        }
        return cartItem;
     }

}

