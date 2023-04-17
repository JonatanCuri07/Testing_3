package com.mirai.importback.repositories;

import com.mirai.importback.entities.DomesticShipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDomesticShipmentRepository extends JpaRepository<DomesticShipment,Long> {
}
