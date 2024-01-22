package com.example.client.config;

//import com.example.library.entities.Admin;
import com.example.library.entities.Customer;
import com.example.library.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceConfig implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer==null){
            throw new UsernameNotFoundException("Could not find username");
        }
        return new User(
                customer.getUsername(),
                customer.getPassword(),
                customer.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
    }

// Cette méthode est généralement utilisée pour charger les détails
// d'un utilisateur à partir de la base de données ou de tout autre système de stockage

}

