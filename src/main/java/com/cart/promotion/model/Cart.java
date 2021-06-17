package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private String cartId;
    private List<CartItem> cartItems;
    private BigDecimal cartTotal;

    public void calculateTotal(PromotionRules promotionRules) {
        cartItems.forEach(CartItem::calculateTotal);
        promotionRules.apply(this);
        cartTotal = cartItems.stream().map(CartItem::getTargetPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

