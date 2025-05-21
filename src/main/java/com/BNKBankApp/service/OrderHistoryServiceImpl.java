package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.OrderHistory;
import com.BNKBankApp.data.repository.OrderHistoryRepo;
import com.BNKBankApp.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepo orderHistoryRepo;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Override
    public OrderHistory saveOrderHistory(Order order, String userId) {
        Cart foundCart = cartServiceImpl.findCartByUserId(userId);
        if(foundCart == null) {throw new CartNotFoundException("Cart Not Found!");}
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(order.getId());
        orderHistory.setUserId(userId);
        orderHistory.setOrderItems(foundCart.getOrderItem());
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
