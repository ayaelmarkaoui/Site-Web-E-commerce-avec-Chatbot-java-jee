package com.example.library.dto;

import com.example.library.entities.City;
import com.example.library.entities.Role;
import com.example.library.entities.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Size(min = 3, max = 15, message = "First name should have  3-15 characters")
    private String firstname;
    @Size(min = 3, max = 15, message = "Last name should have  3-15 characters")
    private String lastname;
    private String username;
    @Size(min = 5, max = 20, message = "Password should have  5-20 characters")
    private String password;
    private String repeatPassword;
    private String country;
    private String city;
    private String adresse;
    private String phoneNumber;

}
