package com.tw.store.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {

    List<ProductPrice> findAllByProductId(String productId);

    ProductPrice save(ProductPrice productPrice);

    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtAsc(String productId);

    Optional<ProductPrice> findFirstByProductIdOrderByCreatedAtDesc(String productId);
}
