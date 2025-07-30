package com.vicheaCoder.food_delivery_api.controller;


import com.vicheaCoder.food_delivery_api.dto.RestaurantRequest;
import com.vicheaCoder.food_delivery_api.dto.RestaurantResponse;
import com.vicheaCoder.food_delivery_api.dto.UserRequest;
import com.vicheaCoder.food_delivery_api.dto.UserResponse;
import com.vicheaCoder.food_delivery_api.model.User;
import com.vicheaCoder.food_delivery_api.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@Valid @RequestBody RestaurantRequest restaurantRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        RestaurantResponse createRestaurant = restaurantService.create(restaurantRequest);
        return ResponseEntity.ok(createRestaurant);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    private ResponseEntity<RestaurantResponse> update(@PathVariable Long id, @RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse updateRestaurant = restaurantService.update(id, restaurantRequest);
        return ResponseEntity.ok(updateRestaurant);
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
        try{
            List<RestaurantResponse> restaurants = restaurantService.getAll();
            return ResponseEntity.ok(restaurants);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            restaurantService.delete(id);
            return ResponseEntity.ok("Restaurant with ID:" + id +" Deleted successfully!");
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return Optional.ofNullable(restaurantService.getById(id))
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Restaurant with ID " + id + " not found."));
    }

}
