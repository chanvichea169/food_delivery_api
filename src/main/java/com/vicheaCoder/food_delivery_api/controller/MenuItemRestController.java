package com.vicheaCoder.food_delivery_api.controller;

import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import com.vicheaCoder.food_delivery_api.dto.RestaurantRequest;
import com.vicheaCoder.food_delivery_api.dto.RestaurantResponse;
import com.vicheaCoder.food_delivery_api.service.MenuItemService;
import com.vicheaCoder.food_delivery_api.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-items")
public class MenuItemRestController {

    private final MenuItemService menuItemService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@Valid @RequestBody MenuItemRequest menuItemRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        MenuItemResponse createMenuItem = menuItemService.createMenuItem(menuItemRequest);
        return ResponseEntity.ok(createMenuItem);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        MenuItemResponse menuItemResponse = menuItemService.getMenuItemById(id);
        if (menuItemResponse == null) {
            return ResponseEntity.status(404).body("Menu item with ID " + id + " not found.");
        }
        return ResponseEntity.ok(menuItemResponse);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody MenuItemRequest menuItemRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            MenuItemResponse updatedMenuItem = menuItemService.updateMenuItem(id, menuItemRequest);
            return ResponseEntity.ok(updatedMenuItem);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Menu item with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok("Menu item with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Menu item with ID " + id + " not found.");
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping(value = "/restaurant/{restaurantId}", produces = "application/json")
    public ResponseEntity<?> getAllMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuItemService.getAllMenuItemsByRestaurantId(restaurantId));
    }
}
