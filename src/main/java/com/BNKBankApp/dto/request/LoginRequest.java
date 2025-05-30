package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequest {


    @NotBlank(message = "This field cannot be blank")
    private String email;

    @NotBlank(message = "This field cannot be blank")
    private String password;

    @NotBlank(message = "This field cannot be blank")
    private String otp;

}
