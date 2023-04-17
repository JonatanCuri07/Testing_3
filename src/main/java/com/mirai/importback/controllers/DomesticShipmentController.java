package com.mirai.importback.controllers;

import com.mirai.importback.entities.DomesticShipment;
import com.mirai.importback.services.IDomesticShipmentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/domesticShipment")
@CrossOrigin
@Api(tags = "domesticShipment", value="Web Services of domestic shipment")
public class DomesticShipmentController {

    private IDomesticShipmentService domesticShipmentService;

    public DomesticShipmentController(IDomesticShipmentService domesticShipmentService) {
        this.domesticShipmentService = domesticShipmentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DomesticShipment>> findAll(){
        try {
            List<DomesticShipment> domesticShipment= domesticShipmentService.getAll();
            if(domesticShipment.size()>0)
                return new ResponseEntity<>(domesticShipment, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomesticShipment> findById(@PathVariable("id")Long id){
        try{
            Optional<DomesticShipment> domesticShipment=domesticShipmentService.getById(id);
            if(!domesticShipment.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(domesticShipment.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomesticShipment> insertDomesticShipment(@Valid @RequestBody DomesticShipment domesticShipment){
        try{
            DomesticShipment domesticShipmentNew= domesticShipmentService.save(domesticShipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(domesticShipmentNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomesticShipment> updateDomesticShipment(@PathVariable("id")Long id,@Valid @RequestBody DomesticShipment domesticShipment){
        try{
            Optional<DomesticShipment> domesticShipmentUpdate= domesticShipmentService.getById(id);
            if(!domesticShipmentUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            domesticShipment.setId(id);
            domesticShipmentService.save(domesticShipment);
            return new ResponseEntity<>(domesticShipment,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DomesticShipment> deleteDomesticShipment(@PathVariable("id")Long id){
        try{
            Optional<DomesticShipment> domesticShipmentDelete=domesticShipmentService.getById(id);
            if(!domesticShipmentDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            domesticShipmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
