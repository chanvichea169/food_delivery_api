package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.MenuItem;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurant(Restaurant restaurant);
}
