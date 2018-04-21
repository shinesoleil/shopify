//package com.tw.store;
//
//import com.tw.store.domain.Product;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@FeignClient(value = "STORE-SERVICE")
//public interface ProductClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
//    Product getProduct(@PathVariable("productId") String productId);
//}