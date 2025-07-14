package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.RestaurantRequest;
import com.vicheaCoder.food_delivery_api.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse create(RestaurantRequest restaurantRequest);
    RestaurantResponse update(Long id, RestaurantRequest restaurantRequest);
    void delete(Long id);
    RestaurantResponse getById(Long id);
    List<RestaurantResponse> getAll();
}
