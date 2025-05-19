package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.OrderHistory;
import com.BNKBankApp.data.repository.OrderHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepo orderHistoryRepo;

    @Override
    public OrderHistory saveOrderHistory(Order order, String userId) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(order.getId());
        orderHistory.setUserId(userId);
        orderHistoryRepo.save(orderHistory);
        return orderHistory;
    }

    @Override
    public void deleteAll() {orderHistoryRepo.deleteAll();}

    @Override
    public List<OrderHistory> findAllOrderHistory() {
        return List.of();
    }
}
