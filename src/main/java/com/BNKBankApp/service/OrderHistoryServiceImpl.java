package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.OrderHistory;
import java.util.List;

public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Override
    public OrderHistory saveOrderHistory(Order order, String userId) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<OrderHistory> findAllOrderHistory() {
        return List.of();
    }
}
