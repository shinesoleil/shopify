package com.tw.store.repository;


import com.tw.store.domain.Cart;
import com.tw.store.domain.CartItem;
import com.tw.store.domain.CartItemRepository;
import com.tw.store.domain.CartRepository;
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
public class CartItemRepositoryTest {

    @Autowired
    CartItemRepository cartItemRepository;

    @Test
    @Transactional
    public void should_create_and_find_cart_item() {
        String productId = UUID.randomUUID().toString();
        int quantity = 10;

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        CartItem createdCartItem =  cartItemRepository.save(cartItem);

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(createdCartItem.getId());

        assertThat(cartItemOptional.isPresent(), is(true));
        assertThat(cartItemOptional.get().getProductId(), is(productId));
        assertThat(cartItemOptional.get().getQuantity(), is(quantity));
    }

    @Test
    @Transactional
    public void should_find_all_cart_items() {
        String productId = UUID.randomUUID().toString();
        int quantity = 10;

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItems = cartItemRepository.findAll();

        assertThat(cartItems.size(), is(1));
    }

//    @Test
//    @Transactional
//    public void should_create_find_list_of_store() {
//        String productId = UUID.randomUUID().toString();
//        String productName = "phone";
//        double unitPrice = 2000;
//
//        ProductPrice productPrice = new ProductPrice();
//        productPrice.setProductId(productId);
//        productPrice.setProductName(productName);
//        productPrice.setUnitPrice(unitPrice);
//
//        ProductPrice created =  productPriceRepository.save(productPrice);
//
//        assertThat(productPriceRepository.findAllByProductId(productId).size(), is(1));
//    }
//
//    @Test
//    @Transactional
//    public void should_create_find_store_by_id() {
//        String productId = UUID.randomUUID().toString();
//        String productName = "phone";
//        double unitPrice = 2000;
//        double unitPrice2 = 5000;
//
//        ProductPrice productPrice = new ProductPrice();
//        productPrice.setProductId(productId);
//        productPrice.setProductName(productName);
//        productPrice.setUnitPrice(unitPrice);
//        ProductPrice createdProductPrice =  productPriceRepository.save(productPrice);
//
//        Optional<ProductPrice> productPriceOptional = productPriceRepository.findById(createdProductPrice.getId());
//
//        assertThat(productPriceOptional.isPresent(), is(true));
//        assertThat(productPriceOptional.get().getUnitPrice(), is(unitPrice));
//
//        productPrice.setUnitPrice(unitPrice2);
//        ProductPrice createdProductPrice2 =  productPriceRepository.save(productPrice);
//
//        Optional<ProductPrice> productPriceOptional2 = productPriceRepository
//                .findFirstByProductIdOrderByCreatedAtAsc(productId);
//
//        assertThat(productPriceOptional2.isPresent(), is(true));
//        assertThat(productPriceOptional.get().getUnitPrice(), is(unitPrice2));
//    }
}