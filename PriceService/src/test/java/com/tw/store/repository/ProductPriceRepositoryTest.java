package com.tw.store.repository;


import com.tw.store.domain.ProductPrice;
import com.tw.store.domain.ProductPriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductPriceRepositoryTest {

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Test
    @Transactional
    public void should_find_nothing() {
        assertThat(productPriceRepository.findAll().size(), is(0));
    }

    @Test
    @Transactional
    public void should_create_find_list_of_store() {
        String productId = UUID.randomUUID().toString();
        String productName = "phone";
        double unitPrice = 2000;

        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductName(productName);
        productPrice.setUnitPrice(unitPrice);
        productPriceRepository.save(productPrice);

        assertThat(productPriceRepository.findAll().size(), is(1));
    }

    @Test
    @Transactional
    public void should_create_find_store_by_id() {
        String productId = UUID.randomUUID().toString();
        String productName = "phone";
        double unitPrice = 2000;

        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductName(productName);
        productPrice.setUnitPrice(unitPrice);
        ProductPrice createdProductPrice = productPriceRepository.save(productPrice);

        Optional<ProductPrice> productPriceOptional = productPriceRepository.findById(createdProductPrice.getId());

        assertThat(productPriceOptional.isPresent(), is(true));
        assertThat(productPriceOptional.get().getUnitPrice(), is(unitPrice));
    }
}