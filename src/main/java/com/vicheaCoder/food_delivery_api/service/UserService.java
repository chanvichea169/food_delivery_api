package com.vicheaCoder.food_delivery_api.service;
import com.vicheaCoder.food_delivery_api.dto.UserRequest;
import com.vicheaCoder.food_delivery_api.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    UserResponse getUserById(Long id);
    void deleteUser(Long id);
    List<UserResponse> findAll();
}
