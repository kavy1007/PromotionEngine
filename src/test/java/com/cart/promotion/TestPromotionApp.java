package com.cart.promotion;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.CartItem;
import com.cart.promotion.model.Item;
import com.cart.promotion.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = PromotionApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPromotionApp {
    @Autowired
    private CartService cartService;

    @Test
    public void testCartService() {
        Cart cart = new Cart();

        Item a = new Item();
        a.setSkuId("A");
        a.setUnitPrice(BigDecimal.valueOf(50));
        CartItem cartItem = new CartItem();
        cartItem.setItem(a);
        cartItem.setOrderedQty(1);

        Item b = new Item();
        b.setSkuId("B");
        b.setUnitPrice(BigDecimal.valueOf(30));
        CartItem cartItemB = new CartItem();
        cartItemB.setItem(b);
        cartItemB.setOrderedQty(1);

        Item c = new Item();
        c.setSkuId("C");
        c.setUnitPrice(BigDecimal.valueOf(20));
        CartItem cartItemC = new CartItem();
        cartItemC.setItem(c);
        cartItemC.setOrderedQty(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cart.setCartItems(cartItems);
        
        cartService.applyPromotions(cart);
    }
}
