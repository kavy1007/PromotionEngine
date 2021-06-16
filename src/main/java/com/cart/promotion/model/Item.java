package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Item {
    private String skuId;
    private BigDecimal unitPrice;
}
