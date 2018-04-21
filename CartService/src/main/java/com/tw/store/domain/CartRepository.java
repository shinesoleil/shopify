package com.tw.store.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Cart save(Cart cart);

    Optional<Cart> findById(String cartId);

    Optional<Cart> findByOwner(String owner);

//    List<ProductPrice> findAllByProductId(String productId);
//
//    ProductPrice save(ProductPrice productPrice);
//
//    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtAsc(String productId);
//
//    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtDesc(String productId);
}