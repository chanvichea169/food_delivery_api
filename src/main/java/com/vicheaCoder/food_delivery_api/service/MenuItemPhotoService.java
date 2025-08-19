package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemPhotoResponse;
import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;


public interface MenuItemPhotoService {
    MenuItemPhotoResponse upload(MenuItemPhotoRequest menuItemPhotoRequest);
    MenuItemPhotoResponse update(MenuItemPhotoRequest menuItemPhotoRequest, Long id);
    MenuItemPhotoResponse getById(Long id);
    void delete(Long id);
}
