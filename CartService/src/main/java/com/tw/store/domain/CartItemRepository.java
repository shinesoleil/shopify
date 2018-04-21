package com.tw.store.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    CartItem save(CartItem cartItem);

//    Optional<CartItem> findById(String cartId);

//    Optional<CartItem> findByOwner(String owner);

//    List<ProductPrice> findAllByProductId(String productId);
//
//    ProductPrice save(ProductPrice productPrice);
//
//    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtAsc(String productId);
//
//    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtDesc(String productId);
}