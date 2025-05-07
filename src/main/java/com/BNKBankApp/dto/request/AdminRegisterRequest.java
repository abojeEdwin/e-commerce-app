package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Role;
import lombok.Data;


@Data
public class AdminRegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
