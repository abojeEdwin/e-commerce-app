package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreateInventoryRequest {

    @NotBlank(message = "This field cannot be blank")
    private String productId;

    @NotNull(message = "This field is required")
    private int stockQuantity;

    @NotBlank(message = "This field cannot be blank")
    private Date lastRestockDate;

}
