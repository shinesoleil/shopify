package com.tw.store.config;

import com.tw.store.controller.OrderController;
import com.tw.store.controller.OrdersController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{
    /**
     * 扫描com.tw.core包，使其识别JAX-RS注解
     */
    public JerseyConfig() {
        register(OrdersController.class);
        register(OrderController.class);
    }
}
