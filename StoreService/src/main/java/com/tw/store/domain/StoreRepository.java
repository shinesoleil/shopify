package com.tw.store.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

}
