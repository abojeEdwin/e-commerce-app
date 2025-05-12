package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Address")
@Data
public class Address {

    @Id
    private String id;
    private String userId;
    private String streetNumber;
    private String streetName;
    private String city;
    private String postalCode;
    private String country;
    private String lgaName;
}
