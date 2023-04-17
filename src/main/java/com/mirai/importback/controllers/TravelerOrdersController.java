package com.mirai.importback.controllers;


import com.mirai.importback.entities.TravelerOrders;

import com.mirai.importback.services.ITravelerOrdersService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/travelerOrders")
@CrossOrigin
@Api(tags = "travelerOrders", value="Web Services of Traveler Orders")
public class TravelerOrdersController {

    private ITravelerOrdersService travelerOrdersService;


    public TravelerOrdersController(ITravelerOrdersService travelerOrdersService) {
        this.travelerOrdersService = travelerOrdersService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TravelerOrders>> findAll(){
        try {
            List<TravelerOrders> travelerOrders= travelerOrdersService.getAll();
            if(travelerOrders.size()>0)
                return new ResponseEntity<>(travelerOrders, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelerOrders> findById(@PathVariable("id")Long id){
        try{
            Optional<TravelerOrders> travelerOrders=travelerOrdersService.getById(id);
            if(!travelerOrders.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(travelerOrders.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelerOrders> insertTravelerOrders(@Valid @RequestBody TravelerOrders travelerOrders){
        try{
            TravelerOrders travelerOrdersNew= travelerOrdersService.save(travelerOrders);
            return ResponseEntity.status(HttpStatus.CREATED).body(travelerOrdersNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelerOrders> updateTravelerOrders(@PathVariable("id")Long id,@Valid @RequestBody TravelerOrders travelerOrders){
        try{
            Optional<TravelerOrders> travelerOrdersUpdate= travelerOrdersService.getById(id);
            if(!travelerOrdersUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            travelerOrders.setId(id);
            travelerOrdersService.save(travelerOrders);
            return new ResponseEntity<>(travelerOrders,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelerOrders> deleteTravelerOrders(@PathVariable("id")Long id){
        try{
            Optional<TravelerOrders> travelerOrdersDelete=travelerOrdersService.getById(id);
            if(!travelerOrdersDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            travelerOrdersService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}