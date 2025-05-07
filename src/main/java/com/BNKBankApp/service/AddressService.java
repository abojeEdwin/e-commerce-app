package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Address;
import com.BNKBankApp.data.model.User;

public interface AddressService {

    Address addAddress(Address address, User user);
    void deleteAll();
    long count();

}
