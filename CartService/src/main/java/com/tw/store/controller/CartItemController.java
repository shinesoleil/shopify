package com.tw.store.controller;//package com.tw.store.controller;

import com.tw.store.domain.CartItem;
import com.tw.store.domain.CartItemRepository;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CartItemController {
    private CartItem cartItem;

    CartItemRepository cartItemRepository;

    public CartItemController(CartItem cartItem, CartItemRepository cartItemRepository) {
        this.cartItem = cartItem;
        this.cartItemRepository = cartItemRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CartItem getCartItem() {
        return cartItem;
    }

    @DELETE
    public Response deleteCartItem() {
        System.out.println(cartItemRepository);
        cartItemRepository.deleteById(cartItem.getId());
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
