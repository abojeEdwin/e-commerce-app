package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dto.resonse.ProcessOrderResponse;

import java.util.List;

public interface OrderService {

    Order createOrder(Cart cart, User user);
    Order saveOrder(Order order);
    void deleteAllOrders();
    Order findOrder(String orderId);
    List<Order> checkListOfOrders();
    ProcessOrderResponse processOrder(String orderId);
    Order findOrderById(String orderId);
}
