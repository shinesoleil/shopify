package com.tw.store.controller;

import com.tw.store.domain.Order;
import com.tw.store.domain.OrderItem;
import com.tw.store.domain.OrderRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void should_create_order_and_order_items() {
        String productName = "phone";
        double amount = 600;
        int quantity = 2;
        String productPriceId = UUID.randomUUID().toString();

        String name = "hao";
        String address = "Beijing";
        String phone = "13099996666";
//        double totalAmount = 1200;


        Map<String, Object> orderItemInfo = new HashMap<>();
        orderItemInfo.put("product_name", productName);
        orderItemInfo.put("quantity", quantity);
        orderItemInfo.put("amount", amount);
        orderItemInfo.put("product_price_id", productPriceId);

        List<Map<String, Object>> orderItemInfos = new ArrayList<>();
        orderItemInfos.add(orderItemInfo);

        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("name", name);
        orderInfo.put("address", address);
        orderInfo.put("phone", phone);
        orderInfo.put("order_items", orderItemInfos);

        String orderId = UUID.randomUUID().toString();
        String orderItemId = UUID.randomUUID().toString();

        OrderItem createdOrderItem = new OrderItem();
        createdOrderItem.setId(orderItemId);
//        createdOrderItem.setOrderId(orderId);
        createdOrderItem.setProductPriceId(productPriceId);
        createdOrderItem.setQuantity(quantity);
        createdOrderItem.setAmount(amount);
        createdOrderItem.setProductName(productName);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(createdOrderItem);

        Order createdOrder = new Order();
        createdOrder.setId(orderId);
        createdOrder.setName(name);
        createdOrder.setPhone(phone);
        createdOrder.setAddress(address);
        createdOrder.setTotalAmount(amount * quantity);
        createdOrder.setOrderItems(orderItems);

        when(orderRepository.save(any())).thenReturn(createdOrder);

        ResponseEntity<String> entity = restTemplate.postForEntity("/orders", orderInfo, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(entity.getHeaders().getLocation().toString(), Matchers.containsString("orders/" + orderId));
    }

//    @Test
//    public void should_400_when_missing_() {
//        String productName = "phone";
//        double amount = 600;
//        int quantity = 2;
//        String productPriceId = UUID.randomUUID().toString();
//
//        String name = "hao";
//        String address = "Beijing";
//        String phone = "13099996666";
////        double totalAmount = 1200;
//
//        Map<String, Object> orderItemInfo = new HashMap<>();
//        orderItemInfo.put("product_name", productName);
//        orderItemInfo.put("quantity", quantity);
//        orderItemInfo.put("amount", amount);
//        orderItemInfo.put("product_price_id", productPriceId);
//
//        List<Map<String, Object>> orderItemInfos = new ArrayList<>();
//        orderItemInfos.add(orderItemInfo);
//
//        Map<String, Object> orderInfo = new HashMap<>();
//        orderInfo.put("name", name);
//        orderInfo.put("address", address);
//        orderInfo.put("phone", phone);
//        orderInfo.put("order_items", orderItemInfos);
//        orderInfo.put("total_amount", 1200);
//
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(null);
//        orderItem.setOrderId(null);
//        orderItem.setProductPriceId(productPriceId);
//        orderItem.setQuantity(quantity);
//        orderItem.setAmount(amount);
//        orderItem.setProductName(productName);
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(orderItem);
//
//        Order order = new Order();
//        order.setId(null);
//        order.setName(name);
//        order.setPhone(phone);
//        order.setAddress(address);
//        order.setTotalAmount(amount * quantity);
//        order.setOrderItems(orderItems);
//
//        String orderId = UUID.randomUUID().toString();
//        String orderItemId = UUID.randomUUID().toString();
//
//        OrderItem createdOrderItem = new OrderItem();
//        createdOrderItem.setId(orderItemId);
//        createdOrderItem.setOrderId(orderId);
//        createdOrderItem.setProductPriceId(productPriceId);
//        createdOrderItem.setQuantity(quantity);
//        createdOrderItem.setAmount(amount);
//        createdOrderItem.setProductName(productName);
//
//        List<OrderItem> createdOrderItems = new ArrayList<>();
//        createdOrderItems.add(createdOrderItem);
//
//        Order createdOrder = new Order();
//        createdOrder.setId(orderId);
//        createdOrder.setName(name);
//        createdOrder.setPhone(phone);
//        createdOrder.setAddress(address);
//        createdOrder.setTotalAmount(amount * quantity);
//        createdOrder.setOrderItems(createdOrderItems);
//
//        when(orderRepository.save(any())).thenReturn(createdOrder);
//
//        ResponseEntity<String> entity = restTemplate.postForEntity("/orders", orderInfo, String.class);
//        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
//        assertThat(entity.getHeaders().getLocation().toString(), Matchers.containsString("orders/" + orderId));
//    }



    @Test
    public void should_find_all() {
        String orderId = UUID.randomUUID().toString();
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<String> entity = restTemplate.getForEntity("/orders", String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("[]"));
    }

    @Test
    public void should_find_order_by_id() {
        String productName = "phone";
        double amount = 600;
        int quantity = 2;
        String productPriceId = UUID.randomUUID().toString();

        String name = "hao";
        String address = "Beijing";
        String phone = "13099996666";

        String productId = UUID.randomUUID().toString();
        String cartItemId = UUID.randomUUID().toString();

        String orderId = UUID.randomUUID().toString();
        String orderItemId = UUID.randomUUID().toString();

        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemId);
//        orderItem.setOrderId(orderId);
        orderItem.setProductPriceId(productPriceId);
        orderItem.setQuantity(quantity);
        orderItem.setAmount(amount);
        orderItem.setProductName(productName);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = new Order();
        order.setId(orderId);
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setTotalAmount(amount * quantity);
        order.setOrderItems(orderItems);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        ResponseEntity<String> entity = restTemplate.getForEntity("/orders/" + orderId, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}
