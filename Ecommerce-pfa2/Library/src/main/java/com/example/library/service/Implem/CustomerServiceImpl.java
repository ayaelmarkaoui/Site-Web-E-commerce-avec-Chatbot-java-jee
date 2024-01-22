package com.example.library.service.Implem;

import com.example.library.dto.CustomerDto;
import com.example.library.entities.Customer;
import com.example.library.repositories.CustomerRepository;
import com.example.library.repositories.RoleRepository;
import com.example.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findByUsername(String username){
        return customerRepository.findByUsername(username);
    }
    @Override
    public CustomerDto save(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setCountry(customerDto.getCountry());
        customer.setRoles(Arrays.asList(roleRepository.findByRoleName("CUSTOMER")));
        Customer customerSave = customerRepository.save(customer);
        return mapperDto(customerSave) ;
    }
    private CustomerDto mapperDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setPassword(customer.getPassword());
        customerDto.setUsername(customer.getUsername());
        customerDto.setCountry(customer.getCountry());
        return customerDto;

    }
    @Override
    public Customer saveInfor(Customer customer){
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());
        customer1.setAdresse(customer.getAdresse());
        customer1.setCity(customer.getCity());
        customer1.setCountry(customer.getCountry());
        customer1.setPhoneNumber(customer.getPhoneNumber());


        return customerRepository.save(customer1);

    }

}