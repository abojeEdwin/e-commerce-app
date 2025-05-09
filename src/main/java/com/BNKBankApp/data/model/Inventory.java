package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Data
@Document(collection="Inventory")
public class Inventory {

    @Id
    private String id;
    private String productId;
    private int stockQuantity;
    private Date lastRestockedDate;

}
