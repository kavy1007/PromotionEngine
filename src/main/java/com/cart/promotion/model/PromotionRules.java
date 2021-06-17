package com.cart.promotion.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "rules")
@Data
@Configuration
@EnableConfigurationProperties
public class PromotionRules {
    private List<PromotionRule> promotionalRules;

    public void apply(Cart cart) {
        applyItemPromotion(cart);
        applyComboPromotion(cart);
    }

    private void applyItemPromotion(Cart cart) {
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
                    int remainingQty = orderedQty % conditionalQty;
                    BigDecimal itemTargetPrice = promotionRule.getTargetPrice().multiply(BigDecimal.valueOf(count))
                            .add(cartItem.getItem().getUnitPrice().multiply(BigDecimal.valueOf(remainingQty)));
                    cartItem.setPromoAppliedQty(count * conditionalQty);
                    cartItem.setRemainingQty(remainingQty);
                    cartItem.setTargetPrice(targetPrice.min(itemTargetPrice));
                    cartItem.setPromoApplied(promotionRule);
                }));
    }

    private void applyComboPromotion(Cart cart) {
        promotionalRules.stream()
                .filter(PromotionRule::isComboPromo)
                .filter(promotionRule -> promotionRule.isComboPromoValid(cart))
                .filter(promotionRule -> Objects.isNull(cart.getPromoApplied())
                        || cart.getPromoApplied().getPriority() < promotionRule.getPriority())
                .forEach(promotionRule -> {
                    List<String> comboItems = promotionRule.getSkuIds();
                    List<CartItem> comboCartItems = cart.getCartItems()
                            .stream()
                            .filter(cartItem -> comboItems.contains(cartItem.getItem().getSkuId()))
                            .collect(Collectors.toList());
                    int comboItemCount = comboCartItems.stream()
                            .map(CartItem::getOrderedQty)
                            .min(Integer::compareTo).get();
                    cart.setCartTotal(promotionRule.getTargetPrice()
                            .multiply(BigDecimal.valueOf(comboItemCount)));
                    comboCartItems.forEach(cartItem -> {
                        cartItem.setPromoAppliedQty(comboItemCount);
                        cartItem.setRemainingQty(cartItem.getOrderedQty() - comboItemCount);
                        cartItem.recalculateTotal();
                    });
                });
    }

}
