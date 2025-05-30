package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdminRegisterRequest {

    @NotBlank(message = "This field cannot be blank")
    private String username;

    @NotBlank(message = "This field cannot be blank")
    private String password;

    @NotBlank(message = "This field cannot be blank")
    private String email;

    @NotBlank(message = "This field cannot be blank")
    private Role role;
}
