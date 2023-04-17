package com.mirai.importback.repositories;


import com.mirai.importback.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCode(String code);
}
