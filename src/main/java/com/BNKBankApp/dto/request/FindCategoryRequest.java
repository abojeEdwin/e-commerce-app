package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindCategoryRequest {

    @NotBlank(message="This field cannot be blank")
    private String categoryName;

}
