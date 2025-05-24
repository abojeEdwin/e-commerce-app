package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.data.repository.OrderRepo;
import com.BNKBankApp.dto.resonse.ProcessOrderResponse;
import com.BNKBankApp.exception.InvalidIdException;
import com.BNKBankApp.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Order createOrder(Cart cart, User user) {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void deleteAllOrders() {

    }

    @Override
    public Order findOrder(String orderId) {
        return orderRepo.findById(orderId).get();
    }

    @Override
    public List<Order> checkListOfOrders() {
        return List.of();
    }

    @Override
    public ProcessOrderResponse processOrder(String orderId) {
        return null;
    }

    @Override
    public Order findOrderById(String orderId) {
        if(orderId == null) {
            throw new InvalidIdException("Id Is Null");
        }
        return orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }


}
