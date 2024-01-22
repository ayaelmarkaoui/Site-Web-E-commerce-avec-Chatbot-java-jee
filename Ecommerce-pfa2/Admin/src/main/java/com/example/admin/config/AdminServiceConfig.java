package com.example.admin.config;

import com.example.library.entities.Admin;
import com.example.library.repositories.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AdminServiceConfig implements UserDetailsService {
    AdminRepository adminRepository;

    public AdminServiceConfig(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminServiceConfig() {

    }

    // Cette méthode est généralement utilisée pour charger les détails
    // d'un utilisateur à partir de la base de données ou de tout autre système de stockage
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin =adminRepository.findAdminByUserName(username);
        if(admin==null){
            throw new UsernameNotFoundException("Could not find username");
        }
        return new User(
                admin.getUserName(),
                admin.getPassword(),
                admin.getRoles().
                        stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList()));
    }
}
