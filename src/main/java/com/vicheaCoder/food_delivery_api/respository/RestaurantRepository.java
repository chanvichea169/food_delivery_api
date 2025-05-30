package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
