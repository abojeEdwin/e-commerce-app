package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindProductRequest {

    @NotBlank(message="This field cannot be blank")
    private String productName;
}
