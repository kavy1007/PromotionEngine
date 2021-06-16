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
        cartTotal = this.cartItems.stream()
                .map(cartItem -> {
                    cartItem.calculateTotal();
                    return cartItem.getTotal().subtract(cartItem.getDiscountTotal());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

