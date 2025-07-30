package com.vicheaCoder.food_delivery_api.service;

import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuItemService {
    MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest);
    MenuItemResponse updateMenuItem(Long id, MenuItemRequest menuItemRequest);
    MenuItemResponse getMenuItemById(Long id);
    void deleteMenuItem(Long id);
    List<MenuItemResponse> getAllMenuItemsByRestaurantId(Long restaurantId);
}
