package com.cart.promotion.controller;

import com.cart.promotion.model.Cart;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1")
public interface IPromoAPI {
    @ApiOperation(
            value = "calculates CartTotal post applying the promotions",
            response = Cart.class)
    @PostMapping(value = "/cart", produces = "application/json", consumes = "application/json")
    ResponseEntity<Cart> calculateCartTotal(
            @ApiParam(value = "Cart Details", required = true)
            @RequestBody
                    Cart cart);
}
