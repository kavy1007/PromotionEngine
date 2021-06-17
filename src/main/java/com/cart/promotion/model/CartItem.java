package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {
    private Item item;
    private int orderedQty;
    private BigDecimal total;
    private BigDecimal targetPrice;
    private PromotionRule promoApplied;
    private int promoAppliedQty;
    private int remainingQty;


    public void calculateTotal() {
        total = item.getUnitPrice().multiply(BigDecimal.valueOf(orderedQty));
        targetPrice = total;
    }

    public void recalculateTotal() {
        total = item.getUnitPrice().multiply(BigDecimal.valueOf(remainingQty));
        targetPrice = total;
    }
}
