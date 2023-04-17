package com.mirai.importback.repositories;

import com.mirai.importback.entities.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductListRepository extends JpaRepository<ProductList,Long> {
}
