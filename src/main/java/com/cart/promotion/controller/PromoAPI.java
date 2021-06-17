package com.cart.promotion.controller;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.PromotionRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromoAPI implements IPromoAPI {
    @Autowired
    private PromotionRules promotionRules;

    @Override
    public ResponseEntity<Cart> calculateCartTotal(Cart cart) {
        cart.setPromotionRules(promotionRules);
        cart.calculateTotal();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
