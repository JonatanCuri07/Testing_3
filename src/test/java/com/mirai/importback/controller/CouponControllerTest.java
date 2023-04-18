package com.mirai.importback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.CouponController;
import com.mirai.importback.entities.Coupon;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.CouponServiceImpl;
import com.mirai.importback.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CouponController.class)
@ActiveProfiles("test")
public class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponServiceImpl couponService;
    private List<Coupon> couponList;

    static final String COUPON_TITLE = "First Order";

    @BeforeEach
    void setUp(){
        couponList = new ArrayList<>();
        couponList.add(new Coupon(1L, COUPON_TITLE,"10","ABC123",
                "09/09/09","true","*Coupons are not accumulative"));
        couponList.add(new Coupon(2L,"Black Friday Week","50","ACD090",
                "09/09/09","true","*Coupons are not accumulative. Only for travelers mode"));
        couponList.add(new Coupon(3L,"ImportIt20","20","ABT555",
                "09/09/09","false",""));
    }

    @Test
    void findAllCouponsTest() throws Exception{
        given(couponService.getAll()).willReturn(couponList);
        mockMvc.perform(get("/api/coupon")).andExpect(status().isOk());
    }

    @Test
    void findCouponsByIdTest() throws Exception{
        Long couponId = 1L;
        Coupon coupon = new Coupon(1L, COUPON_TITLE,"10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        given(couponService.getById(couponId)).willReturn(Optional.of(coupon));
        mockMvc.perform(get("/api/coupon/{id}", couponId)).andExpect(status().isOk());
    }

    @Test
    void insertCouponsTest() throws Exception{
        Coupon coupon = new Coupon(1L,COUPON_TITLE,"10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        mockMvc.perform(post("/api/coupon")
                        .content(asJsonString(coupon))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCouponTest() throws Exception{
        Long id = 1L;
        Coupon coupon = new Coupon(1L,"Second Order","10","ABC123",
                "10/10/10","true","*Coupons are not accumulative");

        given(couponService.getById(id)).willReturn(Optional.of(coupon));
        mockMvc.perform(put("/api/coupon/{id}", id)
                        .content(asJsonString(coupon))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCouponTest() throws Exception{
        Long id = 1L;
        Coupon coupon = new Coupon(1L,"Second Order","10","ABC123",
                "10/10/10","true","*Coupons are not accumulative");

        given(couponService.getById(id)).willReturn(Optional.of(coupon));
        mockMvc.perform(delete("/api/coupon/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void findByCodeTest() throws Exception{
        String couponCode = "ABC123";
        Coupon coupon = new Coupon(1L, COUPON_TITLE,"10","ABC123",
                "09/09/09","true","*Coupons are not accumulative");

        given(couponService.findByCode(couponCode)).willReturn(coupon);
        mockMvc.perform(get("/api/coupon/searchByCode/{code}", couponCode)).andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
