package com.tw.store.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.store.domain.Product;
import com.tw.store.domain.ProductRepository;
import com.tw.store.domain.Store;
import com.tw.store.domain.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void should_create_product() throws Exception {
        String storeId = UUID.randomUUID().toString();
        String storeName = "storeName";
        Store store = new Store();
        store.setId(storeId);
        store.setName(storeName);

        String productId = UUID.randomUUID().toString();
        String productName = "storeName";
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setStoreId(storeId);

        Product productForJson = new Product();
        productForJson.setName(productName);
        productForJson.setStoreId(storeId);
        String json = new ObjectMapper().writeValueAsString(productForJson);

        when(storeRepository.findById(any())).thenReturn(Optional.of(store));

        when(productRepository.save(any())).thenReturn(product);

        mockMvc.perform(post("/stores/" + storeId + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/stores/*/products/*"));
    }

    @Test
    public void should_not_create_product_when_store_not_found() throws Exception {
        String storeId = UUID.randomUUID().toString();

        String productId = UUID.randomUUID().toString();
        String productName = "storeName";
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setStoreId(storeId);

        Product productForJson = new Product();
        productForJson.setName(productName);
        productForJson.setStoreId(storeId);
        String json = new ObjectMapper().writeValueAsString(productForJson);

        when(storeRepository.findById(any())).thenReturn(Optional.empty());

        when(productRepository.save(any())).thenReturn(product);

        mockMvc.perform(post("/stores/" + storeId + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnEmptyList() throws Exception {
        String storeId = UUID.randomUUID().toString();
        String storeName = "storeName";
        Store store = new Store();
        store.setId(storeId);
        store.setName(storeName);

        when(storeRepository.findById(any())).thenReturn(Optional.of(store));
        when(productRepository.findByStoreId(any())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/stores/" + storeId + "/products")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    public void shouldReturnProduct() throws Exception {
        String storeId = UUID.randomUUID().toString();
        String storeName = "storeName";
        Store store = new Store();
        store.setId(storeId);
        store.setName(storeName);

        String productId = UUID.randomUUID().toString();
        String productName = "phone";
        Product product = new Product();
        product.setId(productId);
        product.setStoreId(storeId);
        product.setName(productName);

        when(storeRepository.findById(any())).thenReturn(Optional.of(store));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: " + productId.toString() + ", name: " + productName + ", storeId:" + storeId + "}"));
    }

    @Test
    public void shouldThrowNotFound() throws Exception {
        String storeId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/stores/" + storeId + "/products/" + productId))
                .andExpect(status().isNotFound());
    }
}
