package com.example.library.service.Implem;

import com.example.library.dto.AdminDto;
import com.example.library.entities.Admin;
import com.example.library.repositories.AdminRepository;
import com.example.library.repositories.RoleRepository;
import com.example.library.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;
    RoleRepository roleRepository;

    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Admin findByUserNam(String name) {
        return adminRepository.findAdminByUserName(name);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin=new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUserName(adminDto.getUserName());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByRoleName("Admin")));
        return adminRepository.save(admin);
    }
}
