package com.example.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Shopping_Cart")
public class ShoppingCart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
    private int totalItems;
    private double totalPrices;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItem;

}
