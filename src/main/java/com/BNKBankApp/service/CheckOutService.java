package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.dto.request.AddToCartRequest;

import java.util.List;

public interface CheckOutService {
    Cart addToCart(List<AddToCartRequest> addToCartRequest);
    Order checkOut(String userId);

}
