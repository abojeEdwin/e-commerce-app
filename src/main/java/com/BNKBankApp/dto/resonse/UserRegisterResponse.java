package com.BNKBankApp.dto.resonse;
import lombok.Data;


@Data
public class UserRegisterResponse {
    private String username;
    private String status;
    private String userId;
}
