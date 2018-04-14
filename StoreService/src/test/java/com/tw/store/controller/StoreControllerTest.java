package com.tw.store.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.store.controller.StoreController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreRepository storeRepository;

    @Test
    public void should_create_store() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "name";
        Store store = new Store();
        store.setId(id.toString());
        store.setName(name);

        Store storeForJson = new Store();
        storeForJson.setName(name);
        String json = new ObjectMapper().writeValueAsString(storeForJson);

        when(storeRepository.save(any())).thenReturn(store);

        mockMvc.perform(post("/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/stores/*"));
    }

    @Test
    public void shouldReturnEmptyList() throws Exception {
        when(storeRepository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/stores")).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void shouldReturnStore() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "name";
        Store store = new Store();
        store.setId(id.toString());
        store.setName(name);
        when(storeRepository.findById(id.toString())).thenReturn(Optional.of(store));

        mockMvc.perform(get("/stores/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json("{id: " + id.toString() + ", name: " + name + "}"));
    }

    @Test
    public void shouldThrowNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(storeRepository.findById(id.toString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/stores/" + id.toString()))
                .andExpect(status().isNotFound());
    }
}
