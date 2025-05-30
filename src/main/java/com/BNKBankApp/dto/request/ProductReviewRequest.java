package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Rating;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReviewRequest {

    @NotBlank(message = "This field cannot be blank")
    private String productName;

    @NotBlank(message = "This field cannot be blank")
    private String message;

    @NotBlank(message = "This field cannot be blank")
    private Rating rating;

    @NotBlank(message = "This field cannot be blank")
    private String userId;
}
