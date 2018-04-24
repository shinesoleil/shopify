package com.tw.store;

import com.tw.store.domain.ProductPrice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PRICE-SERVICE")
public interface ProductPriceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}/current-price")
    ProductPrice getPrice(@PathVariable("productId") String productId);
}