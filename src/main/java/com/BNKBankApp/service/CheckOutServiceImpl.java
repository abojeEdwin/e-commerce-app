package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.CartRepo;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.AddToCartRequest;
import com.BNKBankApp.exception.NoProductFoundException;
import com.BNKBankApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CheckOutServiceImpl implements CheckOutService {

    private final CartServiceImpl cartServiceImpl;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    OrderServiceImpl orderServiceImpl;
    @Autowired
    private OrderHistoryServiceImpl orderHistoryServiceImpl;
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    public CheckOutServiceImpl(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

   @Override
    public Cart addToCart(List<AddToCartRequest> addToCartRequest) {
        User foundUser = userRepo.findUserById(addToCartRequest.get(0).getUserId());
        if(foundUser == null) {throw new UserNotFoundException("User not found");}
        return cartServiceImpl.addToCart(addToCartRequest);
    }

    @Override
    public Order checkOut(String userId) {
        Cart cart = cartServiceImpl.findCartByUserId(userId);
        if(cart == null){throw new UserNotFoundException("User not found");}
        User foundUser = userRepo.findUserById(userId);
        if(foundUser == null) {throw new UserNotFoundException("User with Id " + userId + " not found");}

        Order order = new Order();
        order.setOrderDate(cart.getCreatedAt());
        order.setOrderStatus(OrderStatus.COMPLETED_USER);
        order.setBillingAddressId(foundUser.getAddressId());
        order.setTotalPrice(cart.getTotalPrice());
        order.setShippingAddressId(foundUser.getAddressId());
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setUserId(userId);
        Order savedOrder = orderServiceImpl.saveOrder(order);


        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(savedOrder.getId());
        orderHistory.setUserId(foundUser.getId());
        OrderHistory savedOrderHistory = orderHistoryServiceImpl.saveOrderHistory(savedOrder,userId);
        for(OrderItem orderItem : cart.getOrderItem()){
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setCart(cart);
        }
       return orderServiceImpl.saveOrder(savedOrder);
    }

}
