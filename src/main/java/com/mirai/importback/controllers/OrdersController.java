package com.mirai.importback.controllers;

import com.mirai.importback.entities.Orders;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.IOrdersService;
import io.swagger.annotations.Api;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin
@Api(tags = "Orders", value="Web Services of Orders")
public class OrdersController {

    private IOrdersService ordersService;

    public OrdersController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orders>> findAll(){
        try {
            List<Orders> orders= ordersService.getAll();
            if(orders.size()>0)
                return new ResponseEntity<>(orders, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> findById(@PathVariable("id")Long id){
        try{
            Optional<Orders> orders=ordersService.getById(id);
            if(!orders.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(orders.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> insertUsers(@Valid @RequestBody Orders orders){
        try{
            Orders ordersNew= ordersService.save(orders);
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> updateUsers(@PathVariable("id")Long id,@Valid @RequestBody Orders orders){
        try{
            Optional<Orders> ordersUpdate= ordersService.getById(id);
            if(!ordersUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            orders.setId(id);
            ordersService.save(orders);
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> deleteUser(@PathVariable("id")Long id){
        try{
            Optional<Orders> ordersDelete=ordersService.getById(id);
            if(!ordersDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            ordersService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}