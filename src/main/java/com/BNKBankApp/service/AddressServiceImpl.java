package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Address;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.data.repository.AddressRepo;
import com.BNKBankApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepo addressRepo;
//    @Autowired
//    private UserServiceImpl userServiceImpl;

    @Override
    public void deleteAll() {
        addressRepo.deleteAll();
    }

    public long count() {
        return addressRepo.count();
    }

//    @Override
//    public Address save(Address address) {
//        Address savedAddress = new Address();
//        savedAddress.setPostalCode(address.getPostalCode());
//        savedAddress.setCity(address.getCity());
//        savedAddress.setCountry(address.getCountry());
//        savedAddress.setUserId(address.getId());
//        savedAddress.setLgaName(address.getLgaName());
//        savedAddress.setStreetName(address.getStreetName());
//        savedAddress.setStreetNumber(address.getStreetNumber());
//        return addressRepo.save(savedAddress);
//    }
}
