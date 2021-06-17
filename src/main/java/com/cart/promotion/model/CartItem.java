package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class CartItem {
    private Item item;
    private int orderedQty;
    private BigDecimal total;
    private BigDecimal targetPrice;
    private BigDecimal discountTotal = BigDecimal.ZERO;
    private PromotionRule promoApplied;

    public void calculateTotal() {
        total = item.getUnitPrice().multiply(BigDecimal.valueOf(orderedQty));
        targetPrice = total;
        discountTotal = BigDecimal.ZERO;
    }
}
