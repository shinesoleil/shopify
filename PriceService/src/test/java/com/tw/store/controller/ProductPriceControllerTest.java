package com.tw.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.store.ProductClient;
import com.tw.store.domain.Product;
import com.tw.store.domain.ProductPrice;
import com.tw.store.domain.ProductPriceRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//public class ProductPriceControllerTest extends JerseyTest {

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductPriceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductPriceRepository productPriceRepository;

    @MockBean
    private ProductClient productClient;

    @Test
    public void testFetchAll() {
        String productId = UUID.randomUUID().toString();
        when(productPriceRepository.findAllByProductId(eq(productId))).thenReturn(new ArrayList<>());

        ResponseEntity<String> entity = restTemplate.getForEntity("/products/" + productId +"/prices", String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody(), is("[]"));
    }

    @Test
    public void testCreatePrice() {
        double unitPrice = 2000;
        String productName = "phone";
        String productId = UUID.randomUUID().toString();
        String createdPriceId = UUID.randomUUID().toString();

        Map<String, Object> info = new HashMap<>();
        info.put("unit_price", unitPrice);

        ProductPrice productPrice = new ProductPrice();
        productPrice.setUnitPrice(unitPrice);
        productPrice.setProductId(productId);
        productPrice.setProductName(productName);

        ProductPrice createdPrice = new ProductPrice();
        createdPrice.setId(createdPriceId);
        createdPrice.setUnitPrice(unitPrice);
        createdPrice.setProductName(productName);
        createdPrice.setProductId(productId);

        Product product = new Product();
        product.setName(productName);

        when(productClient.getProduct(any())).thenReturn(product);
        when(productPriceRepository.save(ArgumentMatchers.refEq(productPrice, "createdAt"))).thenReturn(createdPrice);

        ResponseEntity<String> entity = restTemplate.postForEntity("/products/"+ productId + "/prices", info, String.class);
        assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(entity.getHeaders().getLocation().toString(), Matchers.containsString("products/" + productId + "/prices/" + createdPriceId));

        when(productPriceRepository.findById(any())).thenReturn(Optional.of(createdPrice));

        ResponseEntity<String> test = restTemplate.getForEntity("/products/" + productId +"/prices/" + createdPriceId, String.class);
        assertThat(test.getStatusCode(), is(HttpStatus.OK));
//        assertThat(test.getBody(), is("{\"id\":\"" + createdPriceId + "\",\"productName\":\"phone\",\"productId\":\"" + productId + "\",\"unitPrice\":2000.0}"));

        when(productPriceRepository.findFirstByProductIdOrderByCreatedAtDesc(any())).thenReturn(Optional.of(createdPrice));

        ResponseEntity<String> current = restTemplate.getForEntity("/products/" + productId +"/current-price", String.class);
        assertThat(current.getStatusCode(), is(HttpStatus.OK));
//        assertThat(current.getBody(), is("{\"id\":\"" + createdPriceId + "\",\"productName\":\"phone\",\"productId\":\"" + productId + "\",\"unitPrice\":2000.0}"));
    }
}
