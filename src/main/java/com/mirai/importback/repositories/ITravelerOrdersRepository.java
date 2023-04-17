package com.mirai.importback.repositories;

import com.mirai.importback.entities.TravelerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravelerOrdersRepository extends JpaRepository<TravelerOrders,Long> {
}
