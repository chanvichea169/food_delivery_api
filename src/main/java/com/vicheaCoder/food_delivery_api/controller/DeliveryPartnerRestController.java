package com.vicheaCoder.food_delivery_api.controller;

import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerRequest;
import com.vicheaCoder.food_delivery_api.dto.DeliveryPartnerResponse;
import com.vicheaCoder.food_delivery_api.dto.UserRequest;
import com.vicheaCoder.food_delivery_api.dto.UserResponse;
import com.vicheaCoder.food_delivery_api.service.DeliveryPartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-partners")
public class DeliveryPartnerRestController {

    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(
            @Valid
            @RequestBody DeliveryPartnerRequest deliveryPartnerRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        DeliveryPartnerResponse createDeliveryPartner = deliveryPartnerService.create(deliveryPartnerRequest);
        return ResponseEntity.ok(createDeliveryPartner);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<DeliveryPartnerResponse> update(
            @PathVariable Long id,
            @RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartnerResponse updatedDeliveryPartner = deliveryPartnerService.update(id, deliveryPartnerRequest);
        return ResponseEntity.ok(updatedDeliveryPartner);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DeliveryPartnerResponse> getById(@PathVariable Long id) {
        DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.getById(id);
        return ResponseEntity.ok(deliveryPartnerResponse);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryPartnerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(deliveryPartnerService.getAll());
    }
}
