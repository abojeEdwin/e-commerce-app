package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Address;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.data.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    AddressServiceImpl addressServiceImpl;

    @BeforeEach
    void setUp() {
        addressServiceImpl.deleteAll();
    }

    @AfterEach
    void tearDown() {
        addressServiceImpl.deleteAll();
    }

    @Test
    void addAddressTest() {
        Address address = new Address();
        address.setCity("Lagos");
        address.setCountry("Nigeria");
        address.setLgaName("Bariga");
        address.setPostalCode("9410");
        address.setStreetName("Olatunji");
        address.setStreetNumber("12");

        User user = new User();
        user.setEmail("example@gmail.com");
        user.setPassword("password");
        user.setUsername("Bruise_Almighty");
        user.setFirstName("Bruise");
        user.setLastName("Almighty");
        user.setPhoneNumber("+234567892");
        user.setRole(Role.User);
        user.setAddress(address);

        String userId = user.getId();

        addressServiceImpl.addAddress(address, userId);
        assertEquals(1,addressServiceImpl.count());
    }


}