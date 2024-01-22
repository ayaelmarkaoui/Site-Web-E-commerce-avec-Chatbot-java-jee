package com.example.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Size(min = 3, max = 15, message = "First name should have  3-15 characters")
    private String firstname;
    @Size(min = 3, max = 15, message = "Last name should have  3-15 characters")
    private String lastname;
    private String country;
    private String phoneNumber;
   @Column(name = "city")
    private String city;
    private String adresse;
    private String password;
    private String username;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)// #LAZY si je charge les donnees d'utilisateur de la bdd il ne va pas charger les donnes des roles d'une maniere automatique
    @JoinTable(name = "customers_roles",
            joinColumns = @JoinColumn(name = "customer_id",referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))

    private Collection<Role> Roles=new ArrayList<>();
    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;
   @OneToMany(mappedBy ="customer")
    private List<Order> orders;

}
