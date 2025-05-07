package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection="Inventory")
public class Inventory {

    @Id
    private String id;
    private String productId;
    private String stockQuantity;
    private Data lastRestockedDate;

}
