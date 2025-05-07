package com.BNKBankApp.dto.request;
import lombok.Data;


@Data
public class AdminRegisterRequest {
    private String username;
    private String password;
    private String email;
}
