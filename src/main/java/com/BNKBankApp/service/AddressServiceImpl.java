package com.BNKBankApp.service;
import com.BNKBankApp.data.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepo addressRepo;

    @Override
    public void deleteAll() {addressRepo.deleteAll();}
    public long count() {return addressRepo.count();}

}
