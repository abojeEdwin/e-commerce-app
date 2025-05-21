package com.BNKBankApp.service;
import com.BNKBankApp.data.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepo orderItemRepo;

    @Override
    public void deleteAll() {
        orderItemRepo.deleteAll();
    }

    @Override
    public void deleteByProductId(String productId) {
        orderItemRepo.removeOrderItemByProductId(productId);
    }
}
