package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;

import java.util.List;

public interface CartService {
    Cart addToCart();
    void deleteAllCart();
    List<Cart> removeProductFromCart(String productName);
}
