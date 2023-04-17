package com.mirai.importback.controllers;

import com.mirai.importback.entities.Orders;
import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.services.IStoreProductsService;
import io.swagger.annotations.Api;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/storeProducts")
@CrossOrigin
@Api(tags = "StoreProducts", value="Web Services of Store Products")
public class StoreProductsController {

    private IStoreProductsService storeProductsService;


    public StoreProductsController(IStoreProductsService storeProductsService) {
        this.storeProductsService = storeProductsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreProducts>> findAll(){
        try {
            List<StoreProducts> storeProducts= storeProductsService.getAll();
            if(storeProducts.size()>0)
                return new ResponseEntity<>(storeProducts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreProducts> findById(@PathVariable("id")Long id){
        try{
            Optional<StoreProducts> storeProducts=storeProductsService.getById(id);
            if(!storeProducts.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(storeProducts.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreProducts> insertStoreProducts(@Valid @RequestBody StoreProducts storeProducts){
        try{
            StoreProducts storeProductsNew= storeProductsService.save(storeProducts);
            return ResponseEntity.status(HttpStatus.CREATED).body(storeProductsNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreProducts> updateStoreProducts(@PathVariable("id")Long id,@Valid @RequestBody StoreProducts storeProducts){
        try{
            Optional<StoreProducts> storeProductsUpdate= storeProductsService.getById(id);
            if(!storeProductsUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            storeProducts.setId(id);
            storeProductsService.save(storeProducts);
            return new ResponseEntity<>(storeProducts,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreProducts> deleteStoreProducts(@PathVariable("id")Long id){
        try{
            Optional<StoreProducts > storeProductsDelete=storeProductsService.getById(id);
            if(!storeProductsDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            storeProductsService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
