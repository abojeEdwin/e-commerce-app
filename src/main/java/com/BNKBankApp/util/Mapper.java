package com.BNKBankApp.util;

import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mapper {
    public static Admin map(AdminRegisterRequest adminRegisterRequest) {
        Admin admin = new Admin();
        BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
        admin.setUsername(adminRegisterRequest.getUsername());
        admin.setEmail(adminRegisterRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(adminRegisterRequest.getPassword());
        admin.setPassword(encodedPassword);
        admin.setRole(Role.Admin);
        return admin;
    }
}
