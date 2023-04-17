package com.mirai.importback.services.impl;


import com.mirai.importback.entities.Coupon;
import com.mirai.importback.repositories.ICouponRepository;
import com.mirai.importback.services.ICouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CouponServiceImpl implements ICouponService {

    private final ICouponRepository couponRepository;

    public CouponServiceImpl(ICouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }


    @Override
    @Transactional
    public Coupon save(Coupon coupon) throws Exception {
        return couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        couponRepository.deleteById(id);

    }

    @Override
    public List<Coupon> getAll() throws Exception {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> getById(Long id) throws Exception {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon findByCode(String code) throws Exception {
        return couponRepository.findByCode(code);
    }
}
