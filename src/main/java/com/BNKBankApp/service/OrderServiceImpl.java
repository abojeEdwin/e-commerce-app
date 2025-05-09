package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dto.resonse.ProcessOrderResponse;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Cart cart, User user) {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return null;
    }

    @Override
    public void deleteAllOrders() {

    }

    @Override
    public Order findOrder(String orderId) {
        return null;
    }

    @Override
    public List<Order> checkListOfOrders() {
        return List.of();
    }

    @Override
    public ProcessOrderResponse processOrder(String orderId) {
        return null;
    }
}
