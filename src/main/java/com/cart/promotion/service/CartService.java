package com.cart.promotion.service;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.PromotionRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private PromotionRules promotionRules;

    public void applyPromotions(Cart cart) {
        cart.calculateTotal(promotionRules);
    }
}
