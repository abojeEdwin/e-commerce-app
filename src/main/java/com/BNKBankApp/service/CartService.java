package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.dto.request.AddToCartRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CartService {
    Cart addToCart(List<AddToCartRequest> addToCartRequest,String userId);
    void deleteAllCart();
    List<Cart> removeProductFromCart(String productName);
    Cart findCartByUserId(String userId);
    Order findOrderById(String orderId);
}
