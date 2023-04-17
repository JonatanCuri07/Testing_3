package com.mirai.importback.services;

import com.mirai.importback.entities.Coupon;

public interface ICouponService extends CrudService<Coupon>{

    Coupon findByCode(String code) throws Exception;
}
