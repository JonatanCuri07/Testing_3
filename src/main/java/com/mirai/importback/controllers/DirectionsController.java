package com.mirai.importback.controllers;

import com.mirai.importback.entities.Directions;
import com.mirai.importback.entities.Orders;
import com.mirai.importback.services.IDirectionsService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/directions")
@CrossOrigin
@Api(tags = "directions", value="Web Services of directions")
public class DirectionsController {

    private IDirectionsService directionsService;

    public DirectionsController(IDirectionsService directionsService) {this.directionsService = directionsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Directions>> findAll(){
        try {
            List<Directions> directions= directionsService.getAll();
            if(!directions.isEmpty())
                return new ResponseEntity<>(directions, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Directions> findById(@PathVariable("id")Long id){
        try{
            Optional<Directions> directions=directionsService.getById(id);
            if(!directions.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(directions.get(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Directions> insertUsers(@Valid @RequestBody Directions directions){
        try{
            Directions directionsNew= directionsService.save(directions);
            return ResponseEntity.status(HttpStatus.CREATED).body(directionsNew);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Directions> updateUsers(@PathVariable("id")Long id,@Valid @RequestBody Directions directions){
        try{
            Optional<Directions> directionsUpdate= directionsService.getById(id);
            if(!directionsUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            directions.setId(id);
            directionsService.save(directions);
            return new ResponseEntity<>(directions,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Directions> deleteUser(@PathVariable("id")Long id){
        try{
            Optional<Directions> directionsDelete=directionsService.getById(id);
            if(!directionsDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            directionsService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}