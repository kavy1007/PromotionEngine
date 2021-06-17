package com.cart.promotion.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Validated
public class PromotionRule {
    private String name;
    private PromotionType promotionType;
    @Positive
    private int conditionQty;
    private BigDecimal targetPrice;
    private List<String> skuIds;
    private int priority;
    private PromoLevel promoLevel;

    public boolean isItemPromo() {
        return getPromoLevel().equals(PromoLevel.ITEM);
    }

    public boolean isItemPromoValid(CartItem cartItem) {
        return skuIds.contains(cartItem.getItem().getSkuId()) && cartItem.getOrderedQty() >= conditionQty;
    }

    public boolean isComboPromo() {
        return getPromoLevel().equals(PromoLevel.COMBO_ITEM);
    }

    public boolean isComboPromoValid(Cart cart) {
        List<String> cartSkuIds = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getOrderedQty() >= conditionQty)
                .map(cartItem -> cartItem.getItem().getSkuId()).collect(Collectors.toList());
        return cartSkuIds.containsAll(skuIds);
    }
}
