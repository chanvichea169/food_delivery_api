package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
