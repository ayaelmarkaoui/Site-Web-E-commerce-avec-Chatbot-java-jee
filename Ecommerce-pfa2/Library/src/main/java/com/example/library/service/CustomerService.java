package com.example.library.service;

import com.example.library.dto.CustomerDto;
import com.example.library.entities.Customer;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);
    Customer findByUsername(String username);
    Customer saveInfor(Customer customer);
}
