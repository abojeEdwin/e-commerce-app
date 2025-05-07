package com.BNKBankApp.dto.resonse;

import com.BNKBankApp.data.model.Role;
import lombok.Data;

@Data
public class AdminRegisterResponse {
    private String username;
    private String email;
    private String status;
    private Role role;
}
