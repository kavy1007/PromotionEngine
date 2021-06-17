package com.cart.promotion;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.CartItem;
import com.cart.promotion.model.Item;
import com.cart.promotion.model.PromotionRules;
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
    private PromotionRules promotionRules;

    @Test
    public void testCartService() {
        Cart cart = new Cart(promotionRules);
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

        cart.calculateTotal();
        Assertions.assertEquals(BigDecimal.valueOf(100), cart.getCartTotal());
    }

    @Test
    public void testCartServiceMultiQty() {
        Cart cart = new Cart(promotionRules);
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

        cart.calculateTotal();
        Assertions.assertEquals(BigDecimal.valueOf(370.0), cart.getCartTotal());

    }

    @Test
    public void testCartServiceMultiQtyCombo() {
        Cart cart = new Cart(promotionRules);
        CartItem cartItem = getCartItemA();
        cartItem.setOrderedQty(3);
        CartItem cartItemB = getCartItemB();
        cartItemB.setOrderedQty(5);
        CartItem cartItemC = getCartItemC();
        cartItemC.setOrderedQty(1);
        CartItem cartItemD = getCartItemD();
        cartItemD.setOrderedQty(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItemB);
        cartItems.add(cartItemC);
        cartItems.add(cartItemD);
        cart.setCartItems(cartItems);
        cart.calculateTotal();
        Assertions.assertEquals(BigDecimal.valueOf(280.0), cart.getCartTotal());
    }

    static CartItem getCartItemD() {
        Item d = new Item();
        d.setSkuId("D");
        d.setUnitPrice(BigDecimal.valueOf(15));
        CartItem cartItemD = new CartItem();
        cartItemD.setItem(d);
        return cartItemD;
    }

    static CartItem getCartItemC() {
        Item c = new Item();
        c.setSkuId("C");
        c.setUnitPrice(BigDecimal.valueOf(20));
        CartItem cartItemC = new CartItem();
        cartItemC.setItem(c);
        return cartItemC;
    }

    static CartItem getCartItemB() {
        Item b = new Item();
        b.setSkuId("B");
        b.setUnitPrice(BigDecimal.valueOf(30));
        CartItem cartItemB = new CartItem();
        cartItemB.setItem(b);
        return cartItemB;
    }

    static CartItem getCartItemA() {
        Item a = new Item();
        a.setSkuId("A");
        a.setUnitPrice(BigDecimal.valueOf(50));
        CartItem cartItem = new CartItem();
        cartItem.setItem(a);
        return cartItem;
    }
}
