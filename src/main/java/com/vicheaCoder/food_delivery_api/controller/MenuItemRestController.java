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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
