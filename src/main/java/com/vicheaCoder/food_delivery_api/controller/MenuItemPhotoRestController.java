package com.vicheaCoder.food_delivery_api.controller;

import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoResponse;
import com.vicheaCoder.food_delivery_api.service.MenuItemPhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-item-photos")
public class MenuItemPhotoRestController {

    private final MenuItemPhotoService menuItemPhotoService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> upload(@Valid @RequestBody MenuItemPhotoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        MenuItemPhotoResponse uploaded = menuItemPhotoService.upload(request);
        return ResponseEntity.ok(uploaded);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(menuItemPhotoService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Photo with ID " + id + " not found.");
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @Valid @RequestBody MenuItemPhotoRequest request,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            MenuItemPhotoResponse updated = menuItemPhotoService.update(request, id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Photo with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            menuItemPhotoService.delete(id);
            return ResponseEntity.ok("Photo with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Photo with ID " + id + " not found.");
        }
    }
}
