package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
