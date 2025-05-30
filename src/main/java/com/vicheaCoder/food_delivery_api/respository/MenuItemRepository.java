package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
