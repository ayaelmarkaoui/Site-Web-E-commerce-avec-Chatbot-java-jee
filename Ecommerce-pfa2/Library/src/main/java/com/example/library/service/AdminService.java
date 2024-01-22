package com.example.library.service;

import com.example.library.dto.AdminDto;
import com.example.library.entities.Admin;

public interface AdminService {
    Admin findByUserNam(String name) ;

    Admin save(AdminDto adminDto);
}
