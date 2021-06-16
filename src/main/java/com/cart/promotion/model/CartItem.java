package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class CartItem {
    private Item item;
    private int orderedQty;
    private BigDecimal total;
    private BigDecimal discountTotal = BigDecimal.ZERO;

    public void calculateTotal() {
        total = item.getUnitPrice().multiply(BigDecimal.valueOf(orderedQty));
        discountTotal = BigDecimal.ZERO;
    }
}
