package com.tw.store.repository;


import com.tw.store.domain.Store;
import com.tw.store.domain.StoreRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    @Transactional
    public void should_find_nothing() {
        assertThat(storeRepository.findAll().size(), is(0));
    }

    @Test
    @Transactional
    public void should_create_find_list_of_store() {
        String name = "hao";
        Store store = new Store();
        store.setName(name);
        storeRepository.save(store);
        assertThat(storeRepository.findAll().size(), is(1));
    }

    @Test
    @Transactional
    public void should_create_find_store_by_id() {
        String name = "hao";
        Store store = new Store();
        store.setName(name);
        Store createdStore = storeRepository.save(store);

        Optional<Store> storeOptional = storeRepository.findById(createdStore.getId());

        assertThat(storeOptional.isPresent(), is(true));
        assertThat(storeOptional.get().getName(), is(name));
    }
}