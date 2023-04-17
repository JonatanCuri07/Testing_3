package com.mirai.importback.repositories;

import com.mirai.importback.entities.StoreProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreProductsRepository extends JpaRepository<StoreProducts,Long> {
}
