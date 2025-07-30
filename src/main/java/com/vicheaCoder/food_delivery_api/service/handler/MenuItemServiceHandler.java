package com.vicheaCoder.food_delivery_api.service.handler;

import com.vicheaCoder.food_delivery_api.constant.Constant;
import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import com.vicheaCoder.food_delivery_api.model.MenuItem;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MenuItemServiceHandler {

    public MenuItem convertMenuItemRequestToMenuItem(MenuItemRequest menuItemRequest,
                                                     MenuItem menuItem,
                                                     Restaurant restaurant) {
        menuItem.setCode(menuItemRequest.getCode());
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setAvailability(menuItemRequest.getAvailability());
        menuItem.setRestaurant(restaurant);
        menuItem.setCreatedAt(new Date());
        menuItem.setCreatedBy(Constant.SYSTEM_USER);
        return menuItem;
    }

    public MenuItemResponse convertMenuItemToMenuItemResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .code(menuItem.getCode())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .availability(menuItem.getAvailability())
                .restaurantId(menuItem.getRestaurant().getId())
                .build();
    }
}
