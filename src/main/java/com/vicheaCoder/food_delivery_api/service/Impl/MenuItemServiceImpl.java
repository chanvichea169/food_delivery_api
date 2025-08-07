package com.vicheaCoder.food_delivery_api.service.Impl;

import com.vicheaCoder.food_delivery_api.dto.MenuItemRequest;
import com.vicheaCoder.food_delivery_api.dto.MenuItemResponse;
import com.vicheaCoder.food_delivery_api.model.MenuItem;
import com.vicheaCoder.food_delivery_api.model.Restaurant;
import com.vicheaCoder.food_delivery_api.respository.MenuItemRepository;
import com.vicheaCoder.food_delivery_api.respository.RestaurantRepository;
import com.vicheaCoder.food_delivery_api.service.MenuItemService;
import com.vicheaCoder.food_delivery_api.service.handler.MenuItemServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemServiceHandler menuItemServiceHandler;

    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(menuItemRequest.getRestaurantId());

        if (restaurantOpt.isEmpty()) {
            log.error("Restaurant with id {} not found", menuItemRequest.getRestaurantId());
            return new MenuItemResponse();
        }

        Restaurant restaurant = restaurantOpt.get();
        MenuItem menuItem = menuItemServiceHandler.convertMenuItemRequestToMenuItem(menuItemRequest, new MenuItem(), restaurant);

        log.info("Creating menu item: {}", menuItem);
        menuItem = menuItemRepository.save(menuItem);

        return menuItemServiceHandler.convertMenuItemToMenuItemResponse(menuItem);
    }

    @Override
    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        Optional<MenuItem> menuItemOpt = menuItemRepository.findById(id);
        if (menuItemOpt.isEmpty()) {
            log.error("Menu item with id {} not found", id);
            return new MenuItemResponse();
        }

        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(request.getRestaurantId());
        if (restaurantOpt.isEmpty()) {
            log.error("Restaurant with id {} not found", request.getRestaurantId());
            return new MenuItemResponse();
        }

        MenuItem updatedItem = menuItemServiceHandler.convertMenuItemRequestToMenuItem(
                request, menuItemOpt.get(), restaurantOpt.get());

        log.info("Updating menu item: {}", updatedItem);
        updatedItem = menuItemRepository.save(updatedItem);

        return menuItemServiceHandler.convertMenuItemToMenuItemResponse(updatedItem);
    }

    @Override
    public MenuItemResponse getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .map(menuItemServiceHandler::convertMenuItemToMenuItemResponse)
                .orElseGet(() -> {
                    log.error("Menu item with id {} not found", id);
                    return new MenuItemResponse();
                });
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            log.error("Menu item with id {} not found", id);
            return;
        }
        menuItemRepository.deleteById(id);
        log.info("Menu item with ID: {} deleted successfully", id);
    }
    @Override
    public List<MenuItemResponse> getAllMenuItemsByRestaurantId(Long restaurantId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (restaurantOpt.isEmpty()) {
            log.error("Restaurant with id {} not found", restaurantId);
            return List.of();
        }

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurant(restaurantOpt.get());
        log.info("Found {} menu items for restaurant with id {}", menuItems.size(), restaurantId);

        return menuItems.stream()
                .map(menuItemServiceHandler::convertMenuItemToMenuItemResponse)
                .toList();
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        log.info("Found {} menu items in total", menuItems.size());

        return menuItems.stream()
                .map(menuItemServiceHandler::convertMenuItemToMenuItemResponse)
                .toList();
    }
}
