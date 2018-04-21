package com.tw.store.config;

import com.tw.store.controller.CartItemController;
import com.tw.store.controller.CartsController;
import com.tw.store.controller.CartsItemsController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{
    /**
     * 扫描com.tw.core包，使其识别JAX-RS注解
     */
    public JerseyConfig() {
        register(CartsItemsController.class);
        register(CartItemController.class);
    }
}
