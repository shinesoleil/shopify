package com.tw.store.repository;


import com.tw.store.domain.Cart;
import com.tw.store.domain.CartItem;
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
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Test
    @Transactional
    public void should_create_and_find_cart() {
        String cartOwner = "hao";
        String productId = UUID.randomUUID().toString();
        int quantity = 10;

        Cart cart = new Cart();
        cart.setOwner(cartOwner);

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        cart.setCartItems(cartItems);

        cartRepository.save(cart);
        Optional<Cart> cartOptional = cartRepository.findByOwner(cartOwner);
        assertThat(cartOptional.isPresent(), is(true));
        assertThat(cartOptional.get().getOwner(), is(cartOwner));
        assertThat(cartOptional.get().getCartItems().size(), is(1));
        assertThat(cartOptional.get().getCartItems().get(0).getQuantity(), is(10));
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