package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Address;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.data.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepo addressRepo;

    @Override
    public Address addAddress(Address address, User user) {
        Address newAddress = new Address();
        newAddress.setStreetName(address.getStreetName());
        newAddress.setCity(address.getCity());
        newAddress.setLgaName(address.getLgaName());
        newAddress.setPostalCode(address.getPostalCode());
        newAddress.setCountry(address.getCountry());
        newAddress.setStreetNumber(address.getStreetNumber());
        newAddress.setStreetName(address.getStreetName());
        newAddress.setUser(user);
        return addressRepo.save(newAddress);
    }

    @Override
    public void deleteAll() {
        addressRepo.deleteAll();
    }

    public long count() {
        return addressRepo.count();
    }
}
