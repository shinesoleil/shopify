package com.tw.store.controller;

import com.tw.store.domain.ProductPrice;
import com.tw.store.domain.ProductPriceRepository;
import com.tw.store.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProductPriceController {

    @Autowired
    ProductPriceRepository productPriceRepository;

    @ResponseBody
    @RequestMapping(method = POST, path = "/products/{pid}/product-prices", consumes = "application/json")
    public ResponseEntity<?> createStore(@RequestBody ProductPrice productPrice) {

        productPrice.setProductId("1234215-3425");
        productPrice.setProductName("phone");

        ProductPrice result =  productPriceRepository.save(productPrice);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
//
//    @ResponseBody
//    @RequestMapping(method = GET, produces = "application/json")
//    public List<Store> readAllStores() {
//        return storeRepository.findAll();
//    }
//
//    @ResponseBody
//    @RequestMapping(method = GET, path = "/{id}", produces = "application/json")
//    public Store readStoreById(@PathVariable("id") String id) {
//        return storeRepository.findById(id).orElseGet(() -> {
//            throw new NotFoundException();
//        });
//    }
}