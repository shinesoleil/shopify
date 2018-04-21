package com.tw.store.controller;

import com.tw.store.domain.Cart;
import com.tw.store.domain.CartItem;
import com.tw.store.domain.CartItemRepository;
import com.tw.store.domain.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Component
@Path("/cart-items")
public class CartsItemsController {

    @Autowired
    CartItemRepository cartItemRepository;

//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    @Autowired
//    ProductClient productClient;
//
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCartItem(HashMap<String, Object> info){
        if (!info.containsKey("product_id")) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(Integer.parseInt(info.get("quantity").toString()));
        cartItem.setProductId(info.get("product_id").toString());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        URI createdLocation = URI.create(String.format("%s/%s", uriInfo.getAbsolutePath(), createdCartItem.getId()));

        return Response.status(Response.Status.CREATED).location(createdLocation).build();
    }


//    @GET
//    @Path("/current-price")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ProductPrice getCurrentPrice(@PathParam("pid") String productId) {
//
//        return productPriceRepository.findFirstByProductIdOrderByCreatedAtDesc(productId)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
//
    @Path("/{ciid}")
    public CartItemController getPrice(@PathParam("ciid") String cartItemId) {

        return cartItemRepository.findById(cartItemId)
                .map((cartItem) -> (new CartItemController(cartItem, cartItemRepository)))
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}