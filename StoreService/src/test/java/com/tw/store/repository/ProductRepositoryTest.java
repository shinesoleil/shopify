package com.tw.store.repository;


import com.tw.store.domain.Product;
import com.tw.store.domain.ProductRepository;
import com.tw.store.domain.Store;
import com.tw.store.domain.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository storeRepository;

    private Store createdStore;

    @Before
    public void setUp() throws Exception {
        String storeName = "hao";
        Store store = new Store();
        store.setName(storeName);
        createdStore = storeRepository.save(store);
    }

    @Test
    @Transactional
    public void should_find_nothing() {
        assertThat(productRepository.findAll().size(), is(0));
    }

    @Test
    @Transactional
    public void should_create_find_list_of_product() {

        String storeId = createdStore.getId();

        String productName = "phone";
        Product product = new Product();
        product.setName(productName);
        product.setStoreId(storeId);
        productRepository.save(product);
        assertThat(productRepository.findAll().size(), is(1));
    }

    @Test
    @Transactional
    public void should_create_find_product_by_id() {
        String storeId = createdStore.getId();

        String productName = "phone";
        Product product = new Product();
        product.setName(productName);
        product.setStoreId(storeId);
        Product createdProduct = productRepository.save(product);

        Optional<Product> productOptional = productRepository.findById(createdProduct.getId());

        assertThat(productOptional.isPresent(), is(true));
        assertThat(productOptional.get().getName(), is(productName));
    }
}