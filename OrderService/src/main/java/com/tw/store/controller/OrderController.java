package com.tw.store.controller;

import com.tw.store.domain.Order;
import com.tw.store.domain.OrderRepository;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrderController {
    private Order order;

    OrderRepository orderRepository;

    public OrderController(Order order, OrderRepository orderRepository) {
        this.order = order;
        this.orderRepository = orderRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder() {
        return order;
    }

    @DELETE
    public Response deleteOrder() {
        orderRepository.deleteById(order.getId());
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
