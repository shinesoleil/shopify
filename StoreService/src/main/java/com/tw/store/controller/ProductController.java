package com.tw.store.controller;


import com.tw.store.domain.Product;
import com.tw.store.domain.ProductRepository;
import com.tw.store.domain.Store;
import com.tw.store.domain.StoreRepository;
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
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProductController {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    ProductRepository productRepository;

    @ResponseBody
    @RequestMapping(method = POST, path = "/stores/{sid}/products", consumes = "application/json")

    public ResponseEntity<?> createStore(@PathVariable String sid,
                                         @RequestBody Product product) {

        Optional<Store> storeOptional = storeRepository.findById(sid);

        storeOptional.orElseGet(() -> {
            throw new NotFoundException();
        });

        product.setStoreId(sid);
        Product result = productRepository.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping(method = GET, path = "/stores/{sid}/products", produces = "application/json")
    public List<Product> readAllStores(@PathVariable String sid) {
//        Optional<Store> storeOptional = storeRepository.findById(sid);
//
//        storeOptional.orElseGet(() -> {
//            throw new NotFoundException();
//        });

        return productRepository.findByStoreId(sid);
    }

    @ResponseBody
    @RequestMapping(method = GET, path = "/products/{pid}", produces = "application/json")
    public Product readProductById(@PathVariable("pid") String pid) {
        return productRepository.findById(pid).orElseGet(() -> {
            throw new NotFoundException();
        });
    }
}
