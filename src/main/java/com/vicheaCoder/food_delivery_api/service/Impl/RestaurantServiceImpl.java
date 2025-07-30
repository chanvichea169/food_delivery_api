package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.RestaurantRequest;
import com.vicheaCoder.food_delivery_api.dto.RestaurantResponse;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import com.vicheaCoder.food_delivery_api.respository.RestaurantRepository;
import com.vicheaCoder.food_delivery_api.service.RestaurantService;
import com.vicheaCoder.food_delivery_api.service.handler.RestaurantHandlerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantHandlerService restaurantHandlerService;

    @Override
    public RestaurantResponse create(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant = restaurantHandlerService
                .convertRestaurantRequestToRestaurant(restaurantRequest, restaurant);

        log.info("Creating restaurant: {}", restaurant);
        restaurantRepository.saveAndFlush(restaurant);


        return restaurantHandlerService.convertRestaurantToRestaurantResponse(restaurant);
    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));

        restaurant = restaurantHandlerService.convertRestaurantRequestToRestaurant(restaurantRequest, restaurant);

        restaurant.setUpdatedAt(new Date());
        restaurant.setUpdatedBy(Constant.SYSTEM_USER);
    
        log.info("Updating restaurant: {}", restaurant);
        restaurantRepository.saveAndFlush(restaurant);

        return restaurantHandlerService.convertRestaurantToRestaurantResponse(restaurant);
    }


    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantResponse getById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantHandlerService::convertRestaurantToRestaurantResponse)
                .orElse(null);
    }


    @Override
    public List<RestaurantResponse> getAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantHandlerService::convertRestaurantToRestaurantResponse)
                .collect(Collectors.toList());
    }

}
