package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Admin")
@Data
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
