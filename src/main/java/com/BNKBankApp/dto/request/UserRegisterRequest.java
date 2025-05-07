package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Address;
import lombok.Data;




@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address address;
    private String phoneNumber;
    private String username;
}
