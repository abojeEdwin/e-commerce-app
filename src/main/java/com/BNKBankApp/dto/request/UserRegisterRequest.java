package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRegisterRequest {

    private String id;

    @NotBlank(message = "This field must not be blank")
    private String firstName;

   @NotBlank(message = "This field must not be blank")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "This field must not be blank")
    private String password;

    @NotBlank(message = "This field must not be blank")
    private String phoneNumber;

    @NotBlank(message = "This field must not be blank")
    private String username;

    private String userId;

    @NotBlank(message = "This field must not be blank")
    private String streetNumber;

    @NotBlank(message = "This field must not be blank")
    private String streetName;

    @NotBlank(message = "This field must not be blank")
    private String city;

    @NotBlank(message = "This field must not be blank")
    @NotEmpty(message="This field cannot be empty")
    private String postalCode;

    @NotBlank(message="This field cannot be empty")
    private String country;

    @NotBlank(message="This field cannot be empty")
    @NotNull(message="This field cannot be null")
    private String lgaName;

}
