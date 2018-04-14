package com.tw.store.controller;

import com.tw.store.domain.Store;
import com.tw.store.domain.StoreRepository;
import com.tw.store.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @ResponseBody
    @RequestMapping(method = POST, consumes = "application/json")
    public ResponseEntity<?> createStore(@RequestBody Store store) {

        Store result =  storeRepository.save(store);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseBody
    @RequestMapping(method = GET, produces = "application/json")
    public List<Store> readAllStores() {
        return storeRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = GET, path = "/{id}", produces = "application/json")
    public Store readStoreById(@PathVariable("id") String id) {
        return storeRepository.findById(id).orElseGet(() -> {
            throw new NotFoundException();
        });
    }
}