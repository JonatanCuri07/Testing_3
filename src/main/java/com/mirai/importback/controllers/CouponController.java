package com.mirai.importback.controllers;

import com.mirai.importback.entities.Coupon;

import com.mirai.importback.services.ICouponService;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coupon")
@CrossOrigin
@Api(tags = "coupon", value="Web Services of coupon")
public class CouponController {

    private ICouponService couponService;


    public CouponController(ICouponService couponService) {this.couponService = couponService;}

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Coupon>> findAll(){
        try {
            List<Coupon> coupon= couponService.getAll();
            if(coupon.size()>0)
                return new ResponseEntity<>(coupon, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coupon> findById(@PathVariable("id")Long id){
        try{
            Optional<Coupon> coupon=couponService.getById(id);
            if(!coupon.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(coupon.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coupon> insertCoupon(@Valid @RequestBody Coupon coupon){
        try{
            Coupon couponNew= couponService.save(coupon);
            return ResponseEntity.status(HttpStatus.CREATED).body(couponNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coupon> updateCoupon(@PathVariable("id")Long id,@Valid @RequestBody Coupon coupon){
        try{
            Optional<Coupon> couponUpdate= couponService.getById(id);
            if(!couponUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            coupon.setId(id);
            couponService.save(coupon);
            return new ResponseEntity<>(coupon,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coupon> deleteCoupon(@PathVariable("id")Long id){
        try{
            Optional<Coupon> couponDelete=couponService.getById(id);
            if(!couponDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            couponService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/searchByCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coupon> findByCode(@PathVariable("code")String code){
        try{
            Coupon coupon=couponService.findByCode(code);
            if(coupon==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(coupon, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
