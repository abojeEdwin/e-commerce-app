package com.BNKBankApp.dto.resonse;
import com.BNKBankApp.data.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class AllProductsInACategoryResponse {

    private List<Product> productList;
    private int total;
}
