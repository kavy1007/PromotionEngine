package com.cart.promotion.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@ConfigurationProperties(prefix = "rules")
@Data
@Configuration
@EnableConfigurationProperties
public class PromotionRules {
    private List<PromotionRule> promotionalRules;

    public void apply(Cart cart) {
        cart.getCartItems().forEach(cartItem -> promotionalRules.stream()
                .filter(PromotionRule::isItemPromo)
                .filter(promotionRule -> promotionRule.isItemPromoValid(cartItem))
                .filter(promotionRule -> Objects.isNull(cartItem.getPromoApplied())
                        || cartItem.getPromoApplied().getPriority() < promotionRule.getPriority())
                .forEach(promotionRule -> {
                    BigDecimal targetPrice = cartItem.getTargetPrice();
                    int orderedQty = cartItem.getOrderedQty();
                    int conditionalQty = promotionRule.getConditionQty();
                    int count = orderedQty / conditionalQty;
                    int remaningQty = orderedQty % conditionalQty;
                    BigDecimal itemTargetPrice = promotionRule.getTargetPrice().multiply(BigDecimal.valueOf(count))
                            .add(cartItem.getItem().getUnitPrice().multiply(BigDecimal.valueOf(remaningQty)));
                    cartItem.setTargetPrice(targetPrice.min(itemTargetPrice));
                    cartItem.setPromoApplied(promotionRule);
                }));
    }

}
