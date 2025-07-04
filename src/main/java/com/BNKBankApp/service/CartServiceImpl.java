package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.CartRepo;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.AddToCartRequest;
import com.BNKBankApp.exception.NoProductFoundException;
import com.BNKBankApp.exception.OrderNotFoundException;
import com.BNKBankApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderItemServiceImpl orderItemServiceImpl;


    @Override
    public Cart addToCart(List<AddToCartRequest> addToCartRequest) {

        double total = 0.0;
        User foundUser = userRepo.findUserById(addToCartRequest.get(0).getUserId());
        if(foundUser == null) {throw new UserNotFoundException("User not found");}


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
        cart.setUserId(addToCartRequest.get(0).getUserId());
        return cartRepo.save(cart);
    }

    @Override
    public void deleteAllCart() {
        cartRepo.deleteAll();
    }
    @Override
    public List<Cart> removeProductFromCart(String productName) {
        Product foundProduct = productServiceImpl.findProduct(productName);

        for(Cart cart : cartRepo.findAll()) {
            List<OrderItem> orderItemsToRemove = new ArrayList<>();
            System.out.println("Before removal : " + cart.getOrderItem());
            for(OrderItem orderItem : cart.getOrderItem()) {
                if(orderItem.getProductId().equals(foundProduct.getId())) {
                    orderItemsToRemove.add(orderItem);
                }
            }
            cart.getOrderItem().removeAll(orderItemsToRemove);
            for(OrderItem orderItem : orderItemsToRemove) {
                orderItemServiceImpl.deleteByProductId(orderItem.getProductId());
            }
            cartRepo.save(cart);
            System.out.println("After removal : " + cart.getOrderItem());
        }
        return cartRepo.findAll();
    }

    @Override
    public Cart findCartByUserId(String userId) {
        Cart foundCart = cartRepo.findByUserId(userId);
        return foundCart;
    }

    @Override
    public Order findOrderById(String orderId) {
        return cartRepo.findByOrderId(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found"));
    }


}
