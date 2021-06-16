package com.cart.promotion.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PromotionRule {
    private String name;
    private PromotionType promotionType;
    private int conditionQty;
    private BigDecimal targetPrice;
    private List<String> skuIds;
    private int priority;
    private String promoLevel;
}
