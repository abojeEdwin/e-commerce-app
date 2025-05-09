package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.OrderHistory;

import java.util.List;


public interface OrderHistoryService {
    OrderHistory saveOrderHistory(Order order, String userId);
    void deleteAll();
    List<OrderHistory> findAllOrderHistory();
}
