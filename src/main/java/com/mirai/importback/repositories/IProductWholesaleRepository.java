package com.mirai.importback.repositories;

import com.mirai.importback.entities.ProductWholesale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductWholesaleRepository extends JpaRepository<ProductWholesale,Long> {
}