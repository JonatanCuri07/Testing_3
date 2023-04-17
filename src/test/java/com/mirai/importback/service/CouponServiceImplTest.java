package com.mirai.importback.service;


import com.mirai.importback.entities.Coupon;
import com.mirai.importback.entities.Users;
import com.mirai.importback.repositories.ICouponRepository;
import com.mirai.importback.services.impl.CouponServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @Mock
    private ICouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    public void saveTest() throws Exception {
        Coupon coupon = new Coupon(1L,"First Order","10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        given(couponService.save(coupon)).willReturn(coupon);

        Coupon savedCoupon= null;
        try{
            savedCoupon = couponService.save(coupon);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(savedCoupon).isNotNull();
        assertEquals(coupon, savedCoupon);
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        couponService.delete(id);
        verify(couponRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<Coupon> list = new ArrayList<>();
        list.add(new Coupon(1L,"First Order","10","ABC123",
                "09/09/09","true","*Coupons are not accumulative"));
        list.add(new Coupon(2L,"Black Friday Week","50","ACD090",
                "09/09/09","true","*Coupons are not accumulative. Only for travelers mode"));
        list.add(new Coupon(3L,"ImportIt20","20","ABT555",
                "09/09/09","false",""));

        given(couponRepository.findAll()).willReturn(list);
        List<Coupon> listExpected = couponService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        Coupon coupon = new Coupon(1L,"First Order","10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        given(couponRepository.findById(id)).willReturn(Optional.of(coupon));
        Optional<Coupon> customerExpected = couponService.getById(id);
        assertThat(customerExpected).isNotNull();
        assertEquals(customerExpected, Optional.of(coupon));
    }

    @Test
    public void findByCodeTest() throws Exception{
        String code = "ABC123";
        Coupon coupon = new Coupon(1L,"First Order","10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        given(couponRepository.findByCode(code)).willReturn(coupon);
        Coupon couponExpected = couponService.findByCode(code);
        assertThat(couponExpected).isNotNull();
        assertEquals(couponExpected, coupon);
    }
}
