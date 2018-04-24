package com.tw.store.repository;

import com.tw.store.domain.Order;
import com.tw.store.domain.OrderItem;
import com.tw.store.domain.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    public void should_find_nothing() {
        assertThat(orderRepository.findAll().size(), is(0));
    }

    @Test
    @Transactional
    public void should_create_order_and_find_by_id() {
        String name = "hao";
        String address = "Beijing";
        String phone = "13099996666";
        double totalAmount = 1200;

        String productName = "phone";
        double amount = 600;
        int quantity = 2;
        String productPriceId = "123-321";

        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(productName);
        orderItem.setAmount(amount);
        orderItem.setQuantity(quantity);
        orderItem.setProductPriceId(productPriceId);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        System.out.println("hahaha");
        System.out.println(order.getOrderItems().get(0).getOrder());
        Order createdOrder = orderRepository.save(order);

        Optional<Order> orderOptional = orderRepository.findById(createdOrder.getId());
        assertThat(orderOptional.isPresent(), is(true));

        Order existingOrder = orderOptional.get();
        assertThat(existingOrder.getAddress(), is(address));
        assertThat(existingOrder.getOrderItems().size(), is(1));
        assertThat(existingOrder.getOrderItems().get(0).getQuantity(), is(quantity));
    }
}