package com.mirai.importback.repositories;

import com.mirai.importback.entities.Directions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectionsRepository extends JpaRepository<Directions,Long> {
}