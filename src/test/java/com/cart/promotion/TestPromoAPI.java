package com.cart.promotion;

import com.cart.promotion.model.Cart;
import com.cart.promotion.model.CartItem;
import com.cart.promotion.model.PromotionRules;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cart.promotion.TestPromotionApp.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = PromotionApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestPromoAPI {
    @LocalServerPort
    int randomServerPort;
    @Autowired
    private PromotionRules promotionRules;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testPromoAPI() {
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
        ResponseEntity cartResponse = promoAPI(cart);
        Assert.assertEquals(HttpStatus.OK, cartResponse.getStatusCode());
    }

    @Test
    public void testPromoAPIBadRequest() {
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
        ResponseEntity cartResponse = promoAPI(cart);
        Assert.assertEquals(HttpStatus.OK, cartResponse.getStatusCode());
    }

    private ResponseEntity promoAPI(Cart cart) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(cart, headers);
        return restTemplate.exchange(
                "http://localhost:" + randomServerPort + "/v1/cart",
                HttpMethod.POST,
                httpEntity,
                Cart.class);
    }

}
