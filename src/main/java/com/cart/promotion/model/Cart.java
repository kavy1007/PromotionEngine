package com.cart.promotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal cartTotal = BigDecimal.ZERO;
    private PromotionRule promoApplied;
    @JsonIgnore
    private PromotionRules promotionRules;

    public Cart(PromotionRules promotionRules) {
        this.promotionRules = promotionRules;
    }

    public Cart() {

    }

    public void calculateTotal() {
        cartItems.forEach(CartItem::calculateTotal);
        promotionRules.apply(this);
        cartTotal = cartTotal.add(cartItems.stream().map(CartItem::getTargetPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}

