package com.BNKBankApp.data.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
public class User {

    @Id
    private String id;
    @NotBlank
    @Indexed(unique = true)
    private String username;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    @Email(message="This field is required")
    private String email;
    private String phoneNumber;
    private Address address;
    private Role role;
}
