package com.cart.promotion;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.CartItem;
import com.cart.promotion.model.Item;
import com.cart.promotion.service.CartService;
import org.junit.jupiter.api.Assertions;
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
        CartItem cartItem = getCartItemA();
        cartItem.setOrderedQty(1);
        CartItem cartItemB = getCartItemB();
        cartItemB.setOrderedQty(1);
        CartItem cartItemC = getCartItemC();
        cartItemC.setOrderedQty(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cart.setCartItems(cartItems);

        cartService.applyPromotions(cart);
        Assertions.assertEquals(BigDecimal.valueOf(100), cart.getCartTotal());
    }

    @Test
    public void testCartServiceMultiQty() {
        Cart cart = new Cart();
        CartItem cartItem = getCartItemA();
        cartItem.setOrderedQty(5);
        CartItem cartItemB = getCartItemB();
        cartItemB.setOrderedQty(5);
        CartItem cartItemC = getCartItemC();
        cartItemC.setOrderedQty(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cart.setCartItems(cartItems);

        cartService.applyPromotions(cart);
        Assertions.assertEquals(BigDecimal.valueOf(370.0), cart.getCartTotal());

    }

    private CartItem getCartItemC() {
        Item c = new Item();
        c.setSkuId("C");
        c.setUnitPrice(BigDecimal.valueOf(20));
        CartItem cartItemC = new CartItem();
        cartItemC.setItem(c);
        return cartItemC;
    }

    private CartItem getCartItemB() {
        Item b = new Item();
        b.setSkuId("B");
        b.setUnitPrice(BigDecimal.valueOf(30));
        CartItem cartItemB = new CartItem();
        cartItemB.setItem(b);
        return cartItemB;
    }

    private CartItem getCartItemA() {
        Item a = new Item();
        a.setSkuId("A");
        a.setUnitPrice(BigDecimal.valueOf(50));
        CartItem cartItem = new CartItem();
        cartItem.setItem(a);
        return cartItem;
    }
}
