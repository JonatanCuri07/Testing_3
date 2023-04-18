package com.mirai.importback.controllers;

import com.mirai.importback.entities.Coupon;
import com.mirai.importback.entities.ProductList;
import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.services.IProductListService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productList")
@CrossOrigin
@Api(tags = "productList", value="Web Services of Product List")
public class ProductListController {

    private IProductListService productListService;


    public ProductListController(IProductListService productListService) {
        this.productListService = productListService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductList>> findAll(){
        try {
            List<ProductList> productList= productListService.getAll();
            if(!productList.isEmpty())
                return new ResponseEntity<>(productList, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductList> findById(@PathVariable("id")Long id){
        try{
            Optional<ProductList> productList=productListService.getById(id);
            if(!productList.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(productList.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductList> insertProductList(@Valid @RequestBody ProductList productList){
        try{
            ProductList productListNew= productListService.save(productList);
            return ResponseEntity.status(HttpStatus.CREATED).body(productListNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductList> updateProductList(@PathVariable("id")Long id,@Valid @RequestBody ProductList productList){
        try{
            Optional<ProductList> productListUpdate= productListService.getById(id);
            if(!productListUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            productList.setId(id);
            productListService.save(productList);
            return new ResponseEntity<>(productList,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductList> deleteProductList(@PathVariable("id")Long id){
        try{
            Optional<ProductList > productListDelete=productListService.getById(id);
            if(!productListDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            productListService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
