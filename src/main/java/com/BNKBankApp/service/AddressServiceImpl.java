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
    public Address addAddress(Address address, String userId) {
//        User foundUser = userServiceImpl.findUserById(userId);
//        if(foundUser == null) {
//            throw new UserNotFoundException("User does not exist");
//        }
        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setLgaName(address.getLgaName());
        newAddress.setPostalCode(address.getPostalCode());
        newAddress.setCountry(address.getCountry());
        newAddress.setStreetNumber(address.getStreetNumber());
        newAddress.setStreetName(address.getStreetName());
//        if(foundUser.getAddress() == null){
//            foundUser.setAddress(newAddress);
//        }
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
