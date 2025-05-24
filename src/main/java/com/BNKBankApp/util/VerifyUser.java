package com.BNKBankApp.util;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.AddressRequest;
import com.BNKBankApp.dto.request.UserRegisterRequest;
import com.BNKBankApp.exception.DuplicateUsernameException;
import com.BNKBankApp.exception.EmailAlreadyExistException;
import com.BNKBankApp.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VerifyUser {
    @Autowired
    UserRepo userRepo;
    @Autowired
    VerifyEmail verifyEmail;

    public void registerUser(UserRegisterRequest userRegisterRequest, AddressRequest addressRequest){
        if(userRepo.existsByEmail(userRegisterRequest.getEmail())){throw new EmailAlreadyExistException("Email already exist");}
        if(!verifyEmail.isVerifiedEmail(userRegisterRequest.getEmail())){throw new InvalidEmailException("Invalid email, please try again.");}
        if(userRepo.existsByUsername(userRegisterRequest.getUsername())){throw new DuplicateUsernameException("Username already exist");}
    }


}
