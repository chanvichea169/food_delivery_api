package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.RestaurantRequest;
import com.vicheaCoder.food_delivery_api.dto.RestaurantResponse;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class RestaurantHandlerService {

    public Restaurant convertRestaurantRequestToRestaurant(RestaurantRequest restaurantRequest, Restaurant restaurant){
        restaurant.setName(restaurantRequest.getName());
        restaurant.setCategory(restaurantRequest.getCategory());
        restaurant.setCode(restaurantRequest.getCode());
        restaurant.setDescription(restaurantRequest.getCode());
        restaurant.setRating(restaurantRequest.getRating());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setLogUrl(restaurantRequest.getLogUrl());
        restaurant.setOpenTime(new Date());
        restaurant.setCloseTime(new Date());
        if (restaurant.getId() == null) {
            restaurant.setCreatedAt(restaurantRequest.getCreatedAt() != null ? restaurantRequest.getCreatedAt() : new Date());
            restaurant.setCreatedBy(restaurantRequest.getCreatedBy() != null ? restaurantRequest.getCreatedBy() : Constant.SYSTEM_USER);
        }

        return restaurant;
    }

    public RestaurantResponse convertRestaurantToRestaurantResponse(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .code(restaurant.getCode())
                .description(restaurant.getDescription())
                .rating(restaurant.getRating())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .logUrl(restaurant.getLogUrl())
                .openTime(restaurant.getOpenTime().toString())
                .closeTime(restaurant.getCloseTime().toString())
                .createdAt(restaurant.getCreatedAt())
                .createdBy(restaurant.getCreatedBy())
                .updatedBy(restaurant.getUpdatedBy())
                .updatedAt(restaurant.getUpdatedAt())
                .build();
    }
}
