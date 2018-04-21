package com.tw.store.controller;

import com.tw.store.domain.Cart;
import com.tw.store.domain.CartItem;
import com.tw.store.domain.CartItemRepository;
import com.tw.store.domain.CartRepository;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

//public class ProductPriceControllerTest extends JerseyTest {

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartItemControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CartItemRepository cartItemRepository;

    @Test
    public void should_create_cart_item() {
        String productId = UUID.randomUUID().toString();
        int quantity = 10;
        String createdCartId = UUID.randomUUID().toString();

        Map<String, Object> info = new HashMap<>();
        info.put("product_id", productId);
        info.put("quantity", quantity);

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        CartItem createdCartItem = new CartItem();
        createdCartItem.setId(createdCartId);
        createdCartItem.setProductId(productId);
        createdCartItem.setQuantity(quantity);

        when(cartItemRepository.save(refEq(cartItem))).thenReturn(createdCartItem);

        ResponseEntity<String> entity = restTemplate.postForEntity("/cart-items", info, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(entity.getHeaders().getLocation().toString(),
                Matchers.containsString("cart-items/" + createdCartId));
    }

    @Test
    public void should_find_cart_item_by_id() {
        String productId = UUID.randomUUID().toString();
        int quantity = 10;
        String cartItemId = UUID.randomUUID().toString();


        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);

        when(cartItemRepository.findById(eq(cartItemId))).thenReturn(Optional.of(cartItem));

        ResponseEntity<String> entity = restTemplate.getForEntity("/cart-items/" + cartItemId, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("{\"id\":\"" + cartItemId + "\",\"productId\":\"" + productId + "\",\"quantity\":" + quantity + "}"));
    }



//    @Test
//    public void should_400_when_missing_params() {
//        String productId = UUID.randomUUID().toString();
//        int quantity = 10;
//        String createdCartId = UUID.randomUUID().toString();
//
//        Map<String, Object> info = new HashMap<>();
//        info.put("product_id", productId);

//        CartItem cartItem = new CartItem();
//        cartItem.setProductId(productId);
//
//        CartItem createdCartItem = new CartItem();
//        createdCartItem.setId(createdCartId);
//        createdCartItem.setProductId(productId);
//        createdCartItem.setQuantity(quantity);
//
//        when(cartItemRepository.save(refEq(cartItem))).thenReturn(createdCartItem);
//
//        ResponseEntity<String> entity = restTemplate.postForEntity("/cart-items", info, String.class);
//        assertThat(entity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
//    }

//    @Test
//    public void testFindCart() {
//        String cartId = UUID.randomUUID().toString();
//        String owner = "hao";
//
//        Map<String, Object> info = new HashMap<>();
//        info.put("owner", owner);
//
//        Cart cart = new Cart();
//        cart.setOwner(owner);
//
//        Cart createdCart = new Cart();
//        createdCart.setOwner(owner);
//        createdCart.setId(cartId);
//
//        when(cartRepository.save(refEq(cart))).thenReturn(createdCart);
//
//        ResponseEntity<String> entity = restTemplate.postForEntity("/carts/", info, String.class);
//        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
//        assertThat(entity.getHeaders().getLocation().toString(), Matchers.containsString("carts/" + cartId));
//
//    }

//    @Test
//    public void testCreatePrice() {
//        double unitPrice = 2000;
//        String productName = "phone";
//        String productId = UUID.randomUUID().toString();
//        String createdPriceId = UUID.randomUUID().toString();
//
//        Map<String, Object> info = new HashMap<>();
//        info.put("unit_price", unitPrice);
//
//        ProductPrice productPrice = new ProductPrice();
//        productPrice.setUnitPrice(unitPrice);
//        productPrice.setProductId(productId);
//        productPrice.setProductName(productName);
//
//        ProductPrice createdPrice = new ProductPrice();
//        createdPrice.setId(createdPriceId);
//        createdPrice.setUnitPrice(unitPrice);
//        createdPrice.setProductName(productName);
//        createdPrice.setProductId(productId);
//
//        Product product = new Product();
//        product.setName(productName);
//
//        when(productClient.getProduct(any())).thenReturn(product);
//        when(productPriceRepository.save(ArgumentMatchers.refEq(productPrice, "createdAt"))).thenReturn(createdPrice);
//
//        ResponseEntity<String> entity = restTemplate.postForEntity("/products/"+ productId + "/prices", info, String.class);
//        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
//        assertThat(entity.getHeaders().getLocation().toString(), Matchers.containsString("products/" + productId + "/prices/" + createdPriceId));
//
//        when(productPriceRepository.findById(any())).thenReturn(Optional.of(createdPrice));
//
//        ResponseEntity<String> test = restTemplate.getForEntity("/products/" + productId +"/prices/" + createdPriceId, String.class);
//        assertThat(test.getStatusCode(), is(HttpStatus.OK));
////        assertThat(test.getBody(), is("{\"id\":\"" + createdPriceId + "\",\"productName\":\"phone\",\"productId\":\"" + productId + "\",\"unitPrice\":2000.0}"));
//
//        when(productPriceRepository.findFirstByProductIdOrderByCreatedAtDesc(any())).thenReturn(Optional.of(createdPrice));
//
//        ResponseEntity<String> current = restTemplate.getForEntity("/products/" + productId +"/current-price", String.class);
//        assertThat(current.getStatusCode(), is(HttpStatus.OK));
////        assertThat(current.getBody(), is("{\"id\":\"" + createdPriceId + "\",\"productName\":\"phone\",\"productId\":\"" + productId + "\",\"unitPrice\":2000.0}"));
//    }
}
