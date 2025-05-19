package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.CartRepo;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.AddToCartRequest;
import com.BNKBankApp.exception.NoProductFoundException;
import com.BNKBankApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderHistoryServiceImpl orderHistoryServiceImpl;
    @Autowired
    private OrderServiceImpl orderServiceImpl;



    @Override
    public Cart addToCart(List<AddToCartRequest> addToCartRequest, String userId) {

        double total = 0.0;
        User foundUser = userRepo.findUserById(userId);
        if(foundUser == null) {throw new UserNotFoundException("User with Id " + userId + " not found");}


        List<OrderItem> orderItems = new ArrayList<>();
        for(AddToCartRequest addToCartRequestItem : addToCartRequest) {
            Product product = productServiceImpl.findProduct(addToCartRequestItem.getProductName());
            if(product != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(addToCartRequestItem.getQuantity());
                orderItem.setProductId(product.getId());
                orderItem.setUnitPrice(product.getPrice());
                orderItems.add(orderItem);
                double result = orderItem.getUnitPrice() * orderItem.getQuantity();
                total += result;
            } else{throw new NoProductFoundException("Product does not exist");}}

        Cart cart = new Cart();
        cart.setOrderItem(orderItems);
        cart.setTotalPrice(total);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUserId(userId);
        return cartRepo.save(cart);
    }

    @Override
    public void deleteAllCart() {
    }
    @Override
    public List<Cart> removeProductFromCart(String productName) {
        return List.of();
    }

    @Override
    public Cart findCartByUserId(String userId) {
        Cart foundCart = cartRepo.findByUserId(userId);
        return foundCart;
    }
}
