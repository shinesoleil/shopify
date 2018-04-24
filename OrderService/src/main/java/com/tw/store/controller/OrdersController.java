package com.tw.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.tw.store.ProductPriceClient;
import com.tw.store.domain.Order;
import com.tw.store.domain.OrderItem;
import com.tw.store.domain.OrderRepository;
import com.tw.store.domain.ProductPrice;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/orders")
public class OrdersController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductPriceClient productPriceClient;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductPrice(HashMap<String, Object> info){

        ObjectMapper mapper = new ObjectMapper();

        Order order = new Order();

        order.setName(info.get("name").toString());
        order.setAddress(info.get("address").toString());
        order.setPhone(info.get("phone").toString());
        order.setName(info.get("name").toString());

        List<Map> orderItemsMap = (List<Map>) info.get("order_items");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Map map: orderItemsMap) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(1000);
            orderItem.setProductName("phone");
            orderItem.setQuantity(Integer.parseInt(map.get("quantity").toString());
            orderItems.add(orderItem)
        }

        order.setOrderItems();
        order.setTotalAmount();
//        try {
//            value = mapper.convertValue(info, Order.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        double totalAmount = 0;
//        System.out.println(value.getOrderItems());
//        for (OrderItem orderItem: value.getOrderItems()) {
//
////            ProductPrice productPrice = productPriceClient.getPrice(orderItem.getProductId());
//            orderItem.setAmount(1500);
//            orderItem.setProductName("phone");
//            orderItem.setProductPriceId("51253-346236");
//
//            totalAmount += orderItem.getAmount() * orderItem.getQuantity();
//            System.out.println(orderItem.getOrder());
//            System.out.println(orderItem.getQuantity());
//        }
//        value.setTotalAmount(totalAmount);

//        System.out.println(value.getOrderItems().get(0).toString());

        Order createdOrder = orderRepository.save(order);

        URI createdLocation = URI.create(String.format("%s/%s", uriInfo.getAbsolutePath(), createdOrder.getId()));

        return Response.status(Response.Status.CREATED).location(createdLocation).build();
    }

    @Path("/{oid}")
    public OrderController getOrder(@PathParam("oid") String orderId) {

        return orderRepository.findById(orderId)
                .map((order) -> (new OrderController(order, orderRepository)))
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

//
//    @GET
//    @Path("/current-price")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ProductPrice getCurrentPrice(@PathParam("pid") String productId) {
//
//        return productPriceRepository.findFirstByProductIdOrderByCreatedAtDesc(productId)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }

}