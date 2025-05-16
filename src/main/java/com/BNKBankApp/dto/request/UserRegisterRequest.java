package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@NotBlank
public class UserRegisterRequest {
    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    @NotEmpty
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String username;

}
