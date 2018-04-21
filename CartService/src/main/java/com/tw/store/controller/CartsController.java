package com.tw.store.controller;

import com.tw.store.domain.Cart;
import com.tw.store.domain.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Component
@Path("/carts")
public class CartsController {

    @Autowired
    CartRepository cartRepository;

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
    public List<Cart> getAllPricesOfProduct(){
        return cartRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductPrice(HashMap<String, Object> info){
//        if (!info.containsKey("unit_price")) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }

        Cart cart = new Cart();
        cart.setOwner(info.get("owner").toString());

        Cart createdCart = cartRepository.save(cart);

        URI createdLocation = URI.create(String.format("%s%s", uriInfo.getAbsolutePath(), createdCart.getId()));

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
//    @Path("/prices/{ppid}")
//    public ProductPriceController getPrice(@PathParam("ppid") String productPriceId) {
//
//        return productPriceRepository.findById(productPriceId)
//                .map(ProductPriceController::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
}